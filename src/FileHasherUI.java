import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class FileHasherUI extends JFrame {
    private JLabel resultLabel;
    private JButton selectFileButton;

    private JLabel hashed;
    private JLabel pathEntered;

    public FileHasherUI() {
        setTitle("File Hasher");
        setSize(750, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        selectFileButton = new JButton("Select File");
        pathEntered = new JLabel("path: ");
        hashed = new JLabel("SHA-256 Hash: ");
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());


        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = j.getSelectedFile().getAbsolutePath();
                    try {
                        String fileHash = FileHasher.hashFile(path);
                        pathEntered.setText("File path" + j.getSelectedFile().getAbsolutePath());
                        add(pathEntered);
                        hashed.setText("SHA-256 Hash: "+ fileHash);
                        add(hashed);
                    } catch (NoSuchAlgorithmException | IOException o) {
                        System.out.println(o);
                    }
                } else {
                    pathEntered.setText("Please select a file");
                }



            }
        });

        add(selectFileButton);
        add(pathEntered);
        add(hashed);

    }
}

