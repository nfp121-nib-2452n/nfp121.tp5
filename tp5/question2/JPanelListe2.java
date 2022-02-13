package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();
    
    private List<String> liste;
    private Map<String, Integer> occurrences;
    private  Stack<List<String>> mem;
   
    
    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;
        mem = new Stack<List<String>>();
        mem.push(liste);
     
        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

       boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        boutonAnnuler.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                mem.push(liste);

                afficheur
                .setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            }
            if(ae.getSource() == boutonAnnuler){
                if(!mem.empty()){
                liste = mem.pop();
            
                
            }
            texte.setText(liste.toString());
            }
            
            texte.setText(liste.toString());    
        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == ordreCroissant)
            {
             
                croissant();
           
                texte.setText(liste.toString());
                mem.push(liste);
             

                
            }
        else if (ie.getSource() == ordreDecroissant)
            {
              
                decroissant();
                 
                texte.setText(liste.toString());
                mem.push(liste);

            }

        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        for(int i=0;i<liste.size();i++){
            if(liste.get(i).equals(prefixe))  {liste.remove(liste.get(i));resultat = true;}
        }
        return resultat;
    }
    
    private void croissant(){
        Collections.sort(liste);
    }
    
    private void decroissant(){
        String temp="";
        String s1="",s2="";
        for(int i=0;i<liste.size();i++){
            for(int j=i+1;j<liste.size()-1;j++){
                if(compare(liste.get(i),liste.get(j)) < 1){
                    s1 = liste.get(i);
                    s2 = liste.get(j);
                    liste.set(i,s2);liste.set(j,s1);
                }
            }
        }
    }
    
    public int compare(Object s,Object s1){
        String s2 = s+"";
        
        String s3 = s1+"";
        return s2.compareTo(s3);
        
    }


}