package fr.servcesimmatriculation.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.servcesimmatriculation.model.EmailTemplate;
import fr.servcesimmatriculation.model.Immatriculation;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

// Permet d'ajouter un caractère à une chaine de  caractère
	public String addChar(String str, char ch, int position) {
		int len = str.length();
		char[] updatedArr = new char[len + 1];
		str.getChars(0, position, updatedArr, 0);
		updatedArr[position] = ch;
		str.getChars(position, len, updatedArr, position + 1);
		return new String(updatedArr);
	}

	// Fonction qui permet de générer un fichier PDF à partir d'un document word et
	// d'envoyer un mail
	// au propriétaire du véhicule
	public void sendNewIdGenerationEmail(final Immatriculation immatriculation, String path, String documentType) {
		try {
			String numerovehicule = addChar(immatriculation.getNumeroimmatriculation(), '-', 2);
			numerovehicule = addChar(numerovehicule, '-', 6);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 30);
			String dateIm = sdf.format(immatriculation.getDatefirstimmatriculation());
			String dateFin = sdf.format(cal.getTime());
			if (documentType.equals("temporaire")) {
				String targetFile = path + "/certificat_temporaire";

				InputStream in = new FileInputStream(new File(path + "/certificat_temporaire.docx"));
				IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

				// 2) Create Java model context
				IContext context = report.createContext();
				context.put("nom", immatriculation.getTitulaireVehicule().getNom() + " "
						+ immatriculation.getTitulaireVehicule().getPrenom());
				context.put("idvehicule", numerovehicule);
				context.put("datecpi", dateIm);
				context.put("dateimmatriculation", dateIm);
				context.put("dateau", dateFin);
				context.put("addresse", immatriculation.getTitulaireVehicule().getAddresse());
				context.put("marque", immatriculation.getDemandeImmatriculation().getMarque());
				context.put("type", immatriculation.getDemandeImmatriculation().getType());
				context.put("chassis", immatriculation.getDemandeImmatriculation().getNumeroidentificationvehicule());

				// 3) Set PDF as format converter
				Options options = Options.getTo(ConverterTypeTo.PDF);

				// 3) Generate report by merging Java model with the ODT and convert it to PDF
				OutputStream out = new FileOutputStream(
						new File(targetFile + immatriculation.getNumeroimmatriculation() + ".pdf"));
				report.convert(context, options, out);
				EmailTemplate emailtemplate = new EmailTemplate("newidvehicule.html");
				Map<String, String> replacements = new HashMap<String, String>();
				replacements.put("nom", immatriculation.getTitulaireVehicule().getNom() + " "
						+ immatriculation.getTitulaireVehicule().getPrenom());
				replacements.put("idvehicule", immatriculation.getNumeroimmatriculation());
				String subject = "Attribution numéro d'immatriculation ";
				String body = emailtemplate.getTemplate(replacements);
				sendEmail(subject, immatriculation.getTitulaireVehicule().getEmail(), body,
						targetFile + immatriculation.getNumeroimmatriculation() + ".pdf");

			} else {
				if (documentType.equals("final")) {
					String targetFile = path + "/certificat_immatriculation";

					InputStream in = new FileInputStream(new File(path + "/certificat_immatriculation.docx"));
					IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

					// 2) Create Java model context
					IContext context = report.createContext();
					context.put("nom", immatriculation.getTitulaireVehicule().getNom() + " "
							+ immatriculation.getTitulaireVehicule().getPrenom());
					context.put("idvehicule", numerovehicule);
					context.put("datecpi", dateIm);
					context.put("dateimmatriculation", dateIm);
					context.put("dateau", dateFin);
					context.put("cotitulaire", immatriculation.getDemandeImmatriculation().getCotitulaire());
					context.put("addresse", immatriculation.getTitulaireVehicule().getAddresse());
					context.put("marque", immatriculation.getDemandeImmatriculation().getMarque());
					context.put("type", immatriculation.getDemandeImmatriculation().getType());
					context.put("chassis",
							immatriculation.getDemandeImmatriculation().getNumeroidentificationvehicule());

					// 3) Set PDF as format converter
					Options options = Options.getTo(ConverterTypeTo.PDF);

					// 3) Generate report by merging Java model with the ODT and convert it to PDF
					OutputStream out = new FileOutputStream(
							new File(targetFile + immatriculation.getNumeroimmatriculation() + ".pdf"));
					report.convert(context, options, out);
					EmailTemplate emailtemplate = new EmailTemplate("cartegrise.html");
					Map<String, String> replacements = new HashMap<String, String>();
					replacements.put("nom", immatriculation.getTitulaireVehicule().getNom() + " "
							+ immatriculation.getTitulaireVehicule().getPrenom());
					replacements.put("idvehicule", immatriculation.getNumeroimmatriculation());
					String subject = "Carte grise à imprimer ";
					String body = emailtemplate.getTemplate(replacements);
					sendEmail(subject, immatriculation.getTitulaireVehicule().getEmail(), body,
							targetFile + immatriculation.getNumeroimmatriculation() + ".pdf");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendEmail(String subject, String to, String message, String targetFile) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("d314dieng@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(message, true);
		FileSystemResource file = new FileSystemResource(targetFile);
		helper.addAttachment(file.getFilename(), file);
		mailSender.send(mimeMessage);
		try {
			Files.deleteIfExists(Paths.get(targetFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
