import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.EventListener;

public class DynamicArray extends JFrame {
    private JPanel contentPane;
    private final JPanel panel = new JPanel();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DynamicArray frame = new DynamicArray();
                frame.setVisible(true);
            }
        });
    }

    private JList list;

    public DynamicArray() {
        setTitle("\u7528\u52A8\u6001\u6570\u7EC4\u4FDD\u5B58\u5B66\u751F\u59D3\u540D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,337,269);
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);

        JScrollPane scrollPane=new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);

        list=new JList();
        scrollPane.setViewportView(list);
    }
}
