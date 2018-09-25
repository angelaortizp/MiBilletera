/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author rfcas
 */
public class PanelGraficoTorta extends JPanel{
    
    public PanelGraficoTorta(){
        super();
        this.values = null;
    }
    
    private List<Double> values;

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        int size   = Math.min( this.getWidth(), this.getHeight() );
        int border = (int)(Math.min( 10d, size*0.1d )/2);        
        g.setColor(Color.WHITE);
        g.fillOval(border+(this.getWidth()-size)/2, border+(this.getHeight()-size)/2, size-2*border, size-2*border);        
                
        if( this.values != null ){
            //
            Double cienPorCiento = 0d;
            for(Double value : this.values){
                cienPorCiento += Math.abs( value );
            }
            int aInicial = 0;
            int aActual  = 0;            
            Random random = new Random();            
            for(Double value : this.values){                                
                aActual = (int)((360d*Math.abs( value )/cienPorCiento));                
                if(aInicial == 0 ){
                    aActual+=1;
                }
                g.setColor( new Color(Math.abs( random.nextInt()%255), Math.abs( random.nextInt()%255), Math.abs( random.nextInt()%255)) );
                g.fillArc(border+(this.getWidth()-size)/2, border+(this.getHeight()-size)/2, size-2*border, size-2*border, aInicial, aActual);                
                aInicial += aActual;
            }
        }
        
        g.fillOval(size, size, WIDTH, HEIGHT);
        
    }  
}
