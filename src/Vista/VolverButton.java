package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolverButton extends JButton{
    private ProgressListener listener;

    public VolverButton(ProgressListener listener) {
        this.setText("Menu Principal");
        this.listener = listener;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.volver();
            }
        });
    }

}
