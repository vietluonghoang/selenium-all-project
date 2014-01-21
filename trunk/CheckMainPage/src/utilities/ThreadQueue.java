/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class ThreadQueue {

    private String currentThreadName = "first thread";
    private ArrayList<String> order;
    private ArrayList<String> alreadyRun;
    private JTextArea taResult;
    private JTextArea taError;
    private Boolean isReady;

    public ThreadQueue(ArrayList<String> order, JTextArea taResult, JTextArea taError) {
        this.order = order;
        this.taResult = taResult;
        this.taError = taError;
    }

    public ThreadQueue(ArrayList<String> order) {
        this.order = order;
        this.alreadyRun=new ArrayList<String>();
    }

    public ArrayList<String> getAlreadyRun() {
        return alreadyRun;
    }

    private void setAlreadyRun(String alreadyRun) {
        this.alreadyRun.add(alreadyRun);
    }

    private String getCurrentThreadName() {
        return currentThreadName;
    }

    private void setCurrentThreadName(String currentThreadName) {
        for (int i = 0; i < order.size(); i++) {
            if (this.order.get(i).equals(currentThreadName)) {
                if (i == order.size() - 1) {
                    i = -1;
                }
                this.currentThreadName = this.order.get(i + 1);
                break;
            }
        }
    }

    public synchronized void showMessage(Thread thread) {

        if (thread.getName().equals(getCurrentThreadName())) {
            setCurrentThreadName(thread.getName());
            setAlreadyRun(thread.getName());
            notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException ex) {
            taResult.append("\nERROR\n\t" + ex.getMessage());
            taError.append("\nERROR\n\t" + ex.getMessage());
        }
    }
}
