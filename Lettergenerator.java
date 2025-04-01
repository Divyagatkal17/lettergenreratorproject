/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lettergenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Divya Gatkal
 */  
public class Lettergenerator {

  
        
    
       public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Letter Generator");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with a grid layout for a professional look
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);

        // Template selection
        JLabel templateLabel = new JLabel("Select Letter Template:");
        templateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        String[] templates = {"Business Letter", "Leave Letter", "Thank You Letter"};
        JComboBox<String> templateComboBox = new JComboBox<>(templates);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(templateLabel, gbc);
        gbc.gridx = 1;
        panel.add(templateComboBox, gbc);

        // Input fields
        JLabel senderLabel = new JLabel("Sender's Name:");
        senderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField senderField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(senderLabel, gbc);
        gbc.gridx = 1;
        panel.add(senderField, gbc);

        JLabel recipientLabel = new JLabel("Recipient's Name:");
        recipientLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField recipientField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(recipientLabel, gbc);
        gbc.gridx = 1;
        panel.add(recipientField, gbc);

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dateChooser, gbc);

        JLabel purposeLabel = new JLabel("Purpose of Letter:");
        purposeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField purposeField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(purposeLabel, gbc);
        gbc.gridx = 1;
        panel.add(purposeField, gbc);
        
        // Add a new field for the letter description
JLabel descriptionLabel = new JLabel("Letter Description:");
descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
JTextArea descriptionArea = new JTextArea(5, 20);
descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
gbc.gridx = 0;
gbc.gridy = 5;
panel.add(descriptionLabel, gbc);
gbc.gridx = 1;
panel.add(descriptionScroll, gbc);
        
        // Formatting options
        JLabel fontLabel = new JLabel("Font:");
        fontLabel.setFont(new Font("Arial", Font.BOLD, 14));
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JComboBox<String> fontComboBox = new JComboBox<>(fonts);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(fontLabel, gbc);
        gbc.gridx = 1;
        panel.add(fontComboBox, gbc);

        JLabel sizeLabel = new JLabel("Font Size:");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        SpinnerNumberModel sizeModel = new SpinnerNumberModel(12, 8, 72, 1);
        JSpinner sizeSpinner = new JSpinner(sizeModel);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(sizeLabel, gbc);
        gbc.gridx = 1;
        panel.add(sizeSpinner, gbc);



        // Generate letter button
        JButton generateButton = new JButton("Generate Letter");
        generateButton.setBackground(Color.BLUE);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(generateButton, gbc);

        // Preview letter button
        JButton previewButton = new JButton("Preview Letter");
        previewButton.setBackground(Color.GREEN);
        previewButton.setForeground(Color.WHITE);
        previewButton.setFocusPainted(false);
        previewButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Layout the buttons side by side
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(previewButton);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);
        
        
         // Export as PDF button
        JButton exportPdfButton = new JButton("Export as PDF");
        exportPdfButton.setBackground(Color.RED);
        exportPdfButton.setForeground(Color.WHITE);
        exportPdfButton.setFocusPainted(false);
        exportPdfButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(exportPdfButton, gbc);

        // Letter preview
        JLabel previewLabel = new JLabel("Generated Letter Preview:");
        previewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextArea previewArea = new JTextArea(10, 50);
        previewArea.setEditable(false);
        previewArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane previewScroll = new JScrollPane(previewArea);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(previewLabel, gbc);
        gbc.gridy = 12;
        panel.add(previewScroll, gbc);

        
       
        // Generate button action
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sender = senderField.getText();
                String recipient = recipientField.getText();
                String description = descriptionArea.getText(); 
                String date = "";
                if (dateChooser.getDate() != null) {
                    date = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
                } else {
                    date = "[Date not selected]";
                }
                String purpose = purposeField.getText();
                String font = (String) fontComboBox.getSelectedItem();
                int fontSize = (int) sizeSpinner.getValue();
                //String alignment = (String) alignmentComboBox.getSelectedItem();

                String template = (String) templateComboBox.getSelectedItem();
                StringBuilder letter = new StringBuilder();

                if (template.equals("Thank You Letter")) {
                    letter.append("[Your Address]\n");
                    letter.append("[City, State, Zip Code]\n");
                    letter.append("[Email Address]\n");
                    letter.append(date).append("\n\n");
                    letter.append(recipient).append("\n");
                    letter.append("[Recipient's Address]\n");
                    letter.append("[City, State, Zip Code]\n\n");
                    letter.append("Dear ").append(recipient).append(",\n\n");
                    letter.append("Description: ").append(description).append("\n\n");
                    letter.append("I hope this message finds you well. I wanted to take a moment to express my heartfelt gratitude for attending my birthday celebration. ");
                    letter.append("Your presence made the day even more special and memorable for me.\n\n");
                    letter.append("It was wonderful to celebrate with friends like you, and I truly appreciate the time you took to be there. ");
                    letter.append("The joy and laughter we shared are moments I will cherish for a long time.\n\n");
                    letter.append("Thank you once again for your thoughtful gift and for being such an important part of my life. ");
                    letter.append("I look forward to creating more beautiful memories together in the future.\n\n");
                    letter.append("Warm regards,\n\n");
                    letter.append(sender);
                } else if (template.equals("Leave Letter")) {
    letter.append("[My Address]  \n");
    letter.append("[City, State, Zip Code]  \n");
    letter.append("[Email Address]  \n");
    letter.append("[Phone Number]  \n");
    letter.append(date).append("\n\n");

    letter.append(recipient).append("  \n");
    letter.append("[Recipient Address]  \n");
    letter.append("[City, State, Zip Code]  \n\n");

    letter.append("Dear ").append(recipient).append(",\n\n");
     letter.append("Description: ").append(description).append("\n\n");
    letter.append("I hope this message finds you well. I am writing to formally request leave for a period of [number of days] starting from [start date] until [end date] due to [reason for leave, e.g., personal matters, health issues, family reasons].\n\n");
    letter.append("I understand the importance of my responsibilities and will ensure a smooth transition of my duties before my absence. I am committed to completing any urgent tasks and will brief [colleague's name] to cover for me during this period. I am confident that my team will continue to perform effectively while I am away.\n\n");
    letter.append("Please let me know if you require any further information or if there are forms or procedures I need to complete prior to my leave. I appreciate your understanding and support regarding this matter.\n\n");
    letter.append("Thank you for considering my request. I look forward to your favorable response.\n\n");
    letter.append("Sincerely,\n\n");
    letter.append(sender).append("\n");
    letter.append("[My Job Title]  \n");
    letter.append("[Company Name]  \n");
    letter.append("[Company Address]");
}
         else if (template.equals("Business Letter")) {
    letter.append("[My Address]  \n");
    letter.append("[City, State, Zip Code]  \n");
    letter.append("[Email Address]  \n");
    letter.append("[Phone Number]  \n");
    letter.append(date).append("\n\n");

    letter.append(recipient).append("  \n");
    letter.append("[Recipient Address]  \n\n");

    letter.append("Dear ").append(recipient).append(",\n\n");
     letter.append("Description: ").append(description).append("\n\n");
    letter.append("I hope this letter finds you well. I am writing to discuss [specific reason for the letter, e.g., a potential partnership opportunity, product inquiry, etc.]. ");
    letter.append("As a professional in the industry, I believe that [explain how the topic relates to both parties and any potential benefits].\n\n");
    letter.append("Our company, [Your Company Name], has been dedicated to [briefly describe your company's mission or primary services/products]. ");
    letter.append("We highly value the importance of collaboration and innovation in achieving our mutual goals. ");
    letter.append("Therefore, I would appreciate the opportunity to connect with you to explore how we can align our efforts and create a strategic partnership.\n\n");
    letter.append("Please let me know a convenient time for you to meet or speak further regarding this matter. ");
    letter.append("I am confident that a discussion will yield valuable insights and opportunities for both our organizations.\n\n");
    letter.append("Thank you for considering this proposal. I look forward to your prompt response.\n\n");
    letter.append("Sincerely,\n\n");
    letter.append(sender).append("\n");
    letter.append("[My Position]  \n");
    letter.append("[Your Company Name]  \n");
    letter.append("[Your Contact Information]");
}

 else {
                    letter.append("Dear ").append(recipient).append(",\n\n");
                    letter.append(purpose).append("\n\n");
                    letter.append("Sincerely,\n").append(sender).append("\n").append(date);
                }

                previewArea.setText(letter.toString());
                previewArea.setFont(new Font(font, Font.PLAIN, fontSize));


            }
            
        });

        
         // Preview button action
       previewButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String sender = senderField.getText();
        String recipient = recipientField.getText();
         String description = descriptionArea.getText(); 
        String date = "";
        if (dateChooser.getDate() != null) {
            date = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
        } else {
            date = "[Date not selected]";
        }
        String purpose = purposeField.getText();
        String font = (String) fontComboBox.getSelectedItem();
        int fontSize = (int) sizeSpinner.getValue();
     

        String template = (String) templateComboBox.getSelectedItem();
        StringBuilder letter = new StringBuilder();

        if (template.equals("Thank You Letter")) {
            letter.append("[Your Address]\n");
            letter.append("[City, State, Zip Code]\n");
            letter.append("[Email Address]\n");
            letter.append(date).append("\n\n");
            letter.append(recipient).append("\n");
            letter.append("[Recipient's Address]\n");
            letter.append("[City, State, Zip Code]\n\n");
            letter.append("Dear ").append(recipient).append(",\n\n");
      letter.append("Description: ").append(description).append("\n\n");
            letter.append("I hope this message finds you well. I wanted to take a moment to express my heartfelt gratitude for attending my birthday celebration. ");
            letter.append("Your presence made the day even more special and memorable for me.\n\n");
            letter.append("It was wonderful to celebrate with friends like you, and I truly appreciate the time you took to be there. ");
            letter.append("The joy and laughter we shared are moments I will cherish for a long time.\n\n");
            letter.append("Thank you once again for your thoughtful gift and for being such an important part of my life. ");
            letter.append("I look forward to creating more beautiful memories together in the future.\n\n");
            letter.append("Warm regards,\n\n");
            letter.append(sender);
        } else if (template.equals("Leave Letter")) {
    letter.append("[My Address]  \n");
    letter.append("[City, State, Zip Code]  \n");
    letter.append("[Email Address]  \n");
    letter.append("[Phone Number]  \n");
    letter.append(date).append("\n\n");

    letter.append(recipient).append("  \n");
    letter.append("[Recipient Address]  \n");
    letter.append("[City, State, Zip Code]  \n\n");

    letter.append("Dear ").append(recipient).append(",\n\n");
    letter.append("Description: ").append(description).append("\n\n");
    letter.append("I hope this message finds you well. I am writing to formally request leave for a period of [number of days] starting from [start date] until [end date] due to [reason for leave, e.g., personal matters, health issues, family reasons].\n\n");
    letter.append("I understand the importance of my responsibilities and will ensure a smooth transition of my duties before my absence. I am committed to completing any urgent tasks and will brief [colleague's name] to cover for me during this period. I am confident that my team will continue to perform effectively while I am away.\n\n");
    letter.append("Please let me know if you require any further information or if there are forms or procedures I need to complete prior to my leave. I appreciate your understanding and support regarding this matter.\n\n");
    letter.append("Thank you for considering my request. I look forward to your favorable response.\n\n");
    letter.append("Sincerely,\n\n");
    letter.append(sender).append("\n");
    letter.append("[My Job Title]  \n");
    letter.append("[Company Name]  \n");
    letter.append("[Company Address]");
}
        else if (template.equals("Business Letter")) {
    letter.append("[My Address]  \n");
    letter.append("[City, State, Zip Code]  \n");
    letter.append("[Email Address]  \n");
    letter.append("[Phone Number]  \n");
    letter.append(date).append("\n\n");

    letter.append(recipient).append("  \n");
    letter.append("[Recipient Address]  \n\n");

    letter.append("Dear ").append(recipient).append(",\n\n");
    letter.append("Description: ").append(description).append("\n\n");
    letter.append("I hope this letter finds you well. I am writing to discuss [specific reason for the letter, e.g., a potential partnership opportunity, product inquiry, etc.]. ");
    letter.append("As a professional in the industry, I believe that [explain how the topic relates to both parties and any potential benefits].\n\n");
    letter.append("Our company, [Your Company Name], has been dedicated to [briefly describe your company's mission or primary services/products]. ");
    letter.append("We highly value the importance of collaboration and innovation in achieving our mutual goals. ");
    letter.append("Therefore, I would appreciate the opportunity to connect with you to explore how we can align our efforts and create a strategic partnership.\n\n");
    letter.append("Please let me know a convenient time for you to meet or speak further regarding this matter. ");
    letter.append("I am confident that a discussion will yield valuable insights and opportunities for both our organizations.\n\n");
    letter.append("Thank you for considering this proposal. I look forward to your prompt response.\n\n");
    letter.append("Sincerely,\n\n");
    letter.append(sender).append("\n");
    letter.append("[My Position]  \n");
    letter.append("[Your Company Name]  \n");
    letter.append("[Your Contact Information]");
}

else {
            letter.append("Dear ").append(recipient).append(",\n\n");
            letter.append(purpose).append("\n\n");
            //letter.append(description).append("\n\n");
            letter.append("Sincerely,\n").append(sender).append("\n").append(date);
        }

        // Create a new JFrame to display the preview of the letter
        JFrame previewFrame = new JFrame("Letter Preview");
        previewFrame.setSize(600, 400);
        previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTextArea for displaying the letter preview
        JTextArea previewArea = new JTextArea();
        previewArea.setEditable(false); // Make it non-editable
        previewArea.setText(letter.toString());
        previewArea.setFont(new Font(font, Font.PLAIN, fontSize));

  

        // Make sure the text wraps properly and looks good
        previewArea.setLineWrap(true);
        previewArea.setWrapStyleWord(true);

        // Add the JTextArea to a JScrollPane for better visibility
        JScrollPane scrollPane = new JScrollPane(previewArea);
        previewFrame.add(scrollPane);

        // Show the preview frame
        previewFrame.setVisible(true);
    }
});
        // Export to PDF button action
exportPdfButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String letter = previewArea.getText(); // Get the content from previewArea
        if (letter.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please generate the letter first.", "Export Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Define folder and file path
            String folderPath = "C:\\Users\\Divya Gatkal\\Downloads\\letters\\";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String recipientName = recipientField.getText().trim();
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName = recipientName.replaceAll("[^a-zA-Z0-9]", "") + "_" + timestamp + ".pdf";
            String savePath = folderPath + fileName;

            // Create and save PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(savePath));
            document.open();
            document.add(new Paragraph(letter));
            document.close();

            JOptionPane.showMessageDialog(frame, "Letter exported to PDF successfully!\nSaved to: " + savePath, "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error exporting to PDF: " + ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

previewButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String sender = senderField.getText();
        String recipient = recipientField.getText();
        String date = "";
        if (dateChooser.getDate() != null) {
            date = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
        } else {
            date = "[Date not selected]";
        }
        String purpose = purposeField.getText();
        String description = descriptionArea.getText();
        String font = (String) fontComboBox.getSelectedItem();
        int fontSize = (int) sizeSpinner.getValue();

        String template = (String) templateComboBox.getSelectedItem();
        StringBuilder letter = new StringBuilder();

        if (template.equals("Thank You Letter")) {
            letter.append("[Your Address]\n");
            letter.append("[City, State, Zip Code]\n");
            letter.append("[Email Address]\n");
            letter.append(date).append("\n\n");
            letter.append(recipient).append("\n");
            letter.append("[Recipient's Address]\n");
            letter.append("[City, State, Zip Code]\n\n");
            letter.append("Dear ").append(recipient).append(",\n\n");
            letter.append("Description: ").append(description).append("\n\n");
            letter.append("I hope this message finds you well. I wanted to take a moment to express my heartfelt gratitude for ").append(description).append(". ");
            letter.append("Your presence made the day even more special and memorable for me.\n\n");
            letter.append("It was wonderful to celebrate with friends like you, and I truly appreciate the time you took to be there. ");
            letter.append("The joy and laughter we shared are moments I will cherish for a long time.\n\n");
            letter.append("Thank you once again for your thoughtful gift and for being such an important part of my life. ");
            letter.append("I look forward to creating more beautiful memories together in the future.\n\n");
            letter.append("Warm regards,\n\n");
            letter.append(sender);
        } else if (template.equals("Leave Letter")) {
            letter.append("[My Address]  \n");
            letter.append("[City, State, Zip Code]  \n");
            letter.append("[Email Address]  \n");
            letter.append("[Phone Number]  \n");
            letter.append(date).append("\n\n");
            letter.append(recipient).append("  \n");
            letter.append("[Recipient Address]  \n");
            letter.append("[City, State, Zip Code]  \n\n");
            letter.append("Dear ").append(recipient).append(",\n\n");
            letter.append("Description: ").append(description).append("\n\n");
            letter.append("I am writing to formally request leave for ").append(description).append(". ");
            letter.append("The reason for my leave is ").append(purpose).append(".\n\n");
            letter.append("I understand the importance of my responsibilities and will ensure a smooth transition of my duties before my absence. ");
            letter.append("I will ensure all urgent tasks are completed and will brief a colleague to cover my responsibilities.\n\n");
            letter.append("Please let me know if any further information is required or procedures need to be completed prior to my leave.\n\n");
            letter.append("Thank you for considering my request.\n\n");
            letter.append("Sincerely,\n\n");
            letter.append(sender);
        } else if (template.equals("Business Letter")) {
            letter.append("[My Address]  \n");
            letter.append("[City, State, Zip Code]  \n");
            letter.append("[Email Address]  \n");
            letter.append("[Phone Number]  \n");
            letter.append(date).append("\n\n");
            letter.append(recipient).append("  \n");
            letter.append("[Recipient Address]  \n\n");
            letter.append("Dear ").append(recipient).append(",\n\n");
            letter.append("Description: ").append(description).append("\n\n");
            letter.append("I hope this letter finds you well. ").append(description).append(".\n\n");
            letter.append("As a professional in the industry, I believe this is an opportunity for mutual benefit and collaboration. ");
            letter.append("Please let me know a convenient time to discuss this further.\n\n");
            letter.append("Thank you for your time and consideration.\n\n");
            letter.append("Sincerely,\n\n");
            letter.append(sender);
        } else {
            letter.append("Dear ").append(recipient).append(",\n\n");
            letter.append(purpose).append("\n\n");
            letter.append(description).append("\n\n");
            letter.append("Sincerely,\n").append(sender).append("\n").append(date);
        }

        // Create a new JFrame to display the preview of the letter
        JFrame previewFrame = new JFrame("Letter Preview");
        previewFrame.setSize(600, 400);
        previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTextArea for displaying the letter preview
        JTextArea previewArea = new JTextArea();
        previewArea.setEditable(false); // Make it non-editable
        previewArea.setText(letter.toString());
        previewArea.setFont(new Font(font, Font.PLAIN, fontSize));

        // Make sure the text wraps properly and looks good
        previewArea.setLineWrap(true);
        previewArea.setWrapStyleWord(true);

        // Add the JTextArea to a JScrollPane for better visibility
        JScrollPane scrollPane = new JScrollPane(previewArea);
        previewFrame.add(scrollPane);

        // Show the preview frame
        previewFrame.setVisible(true);
    }
});


        frame.setVisible(true);
    }
}