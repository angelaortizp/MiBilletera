/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author rfcas
 */
public class PanelGraficoLineas extends JPanel{
    
    public PanelGraficoLineas(){
        super();
        this.valuesX = null;
        this.valuesY = null;
    }
    
    private List<Date>   valuesX;
    private List<Double> valuesY;
  
    public void setValues(List<Date> valuesX, List<Double> valuesY) {
        this.valuesX = valuesX;
        this.valuesY = valuesY;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        int size   = Math.min( this.getWidth(), this.getHeight() );
        int border = (int)(Math.min( 10d, size*0.1d )/2);
        
        g.setColor(Color.WHITE);
        //g.fillRect(border+(this.getWidth()-size)/2, border+(this.getHeight()-size)/2, size-2*border, size-2*border);        
        g.fillRect(border, border, this.getWidth()-2*border, this.getHeight()-2*border);
                
        if( this.valuesX != null && this.valuesY != null ){
            Long minX = Long.MAX_VALUE;
            Long maxX = Long.MIN_VALUE;
            for(Date value : this.valuesX){                
                minX = Math.min(minX, value.getTime());
                maxX = Math.max(maxX, value.getTime());                
            }               
            //
            Double minY = Double.MAX_VALUE;
            Double maxY = Double.MIN_VALUE;
            for(Double value : this.valuesY){
                minY = Math.min(minY, value);
                maxY = Math.max(maxY, value);
            }
            //
            Long valueX, oldValueX = null;
            Double valueY, oldValueY = null;            
            Random random = new Random();  
            for( int index = 0; index < this.valuesX.size(); index++ ){
                valueX = this.valuesX.get(index).getTime();
                valueY = this.valuesY.get(index);
                /**
                 * Y = ((b-a)/(max-min))*(X-min)+a
                 */                
                valueX = (((this.getWidth()-6*border)-0)/(maxX-minX))*(valueX-minX)+3*border;
                valueY = (((this.getHeight()-6*border)-0)/(maxY-minY))*(valueY-minY)+3*border;
                g.setColor( new Color(Math.abs( random.nextInt()%255), Math.abs( random.nextInt()%255), Math.abs( random.nextInt()%255)) );
                if( oldValueX != null && oldValueY != null ){
                   g.drawLine(oldValueX.intValue(), oldValueY.intValue(), valueX.intValue(), valueY.intValue());
                }
                g.fillOval(valueX.intValue()-5, valueY.intValue()-5, 10, 10);
                //
                oldValueX = valueX;
                oldValueY = valueY;
            }
            
        }
        
        //g.fillOval(size, size, WIDTH, HEIGHT);
        
    }  
}
