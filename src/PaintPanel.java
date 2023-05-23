import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PaintPanel extends JPanel {
   private static final int DEFAULT_WIDTH = 800; // константа ширины экрана
   private static final int DEFAULT_HEIGHT = 800; // константа высоты экрана
   private static final Color BACK_COLOR = Color.WHITE; // поле заднего фоно

   private int x1, y1, x2, y2; // координаты 1 и 2 точек


   private Graphics g; // поле графики

   private static Color LineColor = Color.red; // поле цвета линии

   public static void setLineColor(Color lineColor) { // метод изменения цвета линии
      LineColor = lineColor;
   }



   public static void main(String[] args) { // главный метод
      PaintPanel paintPanel = new PaintPanel();
      JFrame frame = new JFrame("Paint program simple"); // создание окна с названием
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // задание функции завершения работы программы при закрытии окна


      frame.getContentPane().setLayout(new FlowLayout()); // задание разметки окну


      // создание иконки для отображения текущего цвета
      JPanel currentColorPanel = new JPanel();
      currentColorPanel.setBackground(LineColor);
      currentColorPanel.setSize(100, 100);






      // создание объектов слайдеров для ргб цветов
      JSlider greenColorSlider = new JSlider();
      JSlider redColorSlider = new JSlider();
      JSlider blueColorSlider = new JSlider();


      // создание объектов подписи к слайдерам цветов
      JLabel redSliderLabel = new JLabel("Красный");
      JLabel greenSliderLabel = new JLabel("Зеленый");
      JLabel blueSliderLabel = new JLabel("Синий");

      greenColorSlider.setMaximum(255); // задание максимального значения для слайдера зеленого цвета
      greenColorSlider.setMinimum(0); // задание минимального значения для слайдера зеленого цвета

      redColorSlider.setMaximum(255); // задание максимального значения для слайдера красного цвета
      redColorSlider.setMinimum(0); // задание минимального значения для слайдера красного цвета

      blueColorSlider.setMaximum(255); // задание максимального значения для слайдера синего цвета
      blueColorSlider.setMinimum(0); // задание минимального значения для слайдера синего цвета


      // Обработка скрола слайдеров цветов
      greenColorSlider.addChangeListener(new ChangeListener() { // обработчик событиый при изменении слайдера зеленого цвета
         @Override
         public void stateChanged(ChangeEvent evt){ // метод изменения состояния
            setLineColor(new Color(redColorSlider.getValue(), greenColorSlider.getValue(), blueColorSlider.getValue())); // задание цвета линии
            currentColorPanel.setBackground(new Color(redColorSlider.getValue(), greenColorSlider.getValue(), blueColorSlider.getValue())); // задание цвета отображения квадрата текущего цвета
         }
      });
      redColorSlider.addChangeListener(new ChangeListener() { // обработчик событиый при изменении слайдера красного цвета
         @Override
         public void stateChanged(ChangeEvent e) { // метод изменения состояния
            setLineColor(new Color(redColorSlider.getValue(), greenColorSlider.getValue(), blueColorSlider.getValue())); // задание цвета линии
            currentColorPanel.setBackground(new Color(redColorSlider.getValue(), greenColorSlider.getValue(), blueColorSlider.getValue())); // задание цвета отображения квадрата текущего цвета
         }
      });
      blueColorSlider.addChangeListener(new ChangeListener() { // обработчик событиый при изменении слайдера синего цвета
         @Override
         public void stateChanged(ChangeEvent e) { // метод изменения состояния
            setLineColor(new Color(redColorSlider.getValue(), greenColorSlider.getValue(), blueColorSlider.getValue())); // задание цвета линии
            currentColorPanel.setBackground(new Color(redColorSlider.getValue(), greenColorSlider.getValue(), blueColorSlider.getValue())); // задание цвета отображения квадрата текущего цвета
         }
      });



      JPanel redColorsSliderWrapper = new JPanel(); // создание объекта обертки для слайдера красного цвета
      JPanel greenColorsSliderWrapper = new JPanel(); // создание объекта обертки для слайдера зеленого цвета
      JPanel blueColorsSliderWrapper = new JPanel(); // создание объекта обертки для слайдера синего цвета

      redColorsSliderWrapper.add(redSliderLabel); // добавление в обертку слайдера красного цвета подпись текста
      redColorsSliderWrapper.add(redColorSlider); // добавление в обертку слайдера красного цвета сам слайдер

      greenColorsSliderWrapper.add(greenSliderLabel); // добавление в обертку слайдера зеленого цвета подпись текста
      greenColorsSliderWrapper.add(greenColorSlider); // добавление в обертку слайдера зеленого цвета сам слайдер

      blueColorsSliderWrapper.add(blueSliderLabel); // добавление в обертку слайдера синего цвета подпись текста
      blueColorsSliderWrapper.add(blueColorSlider); // добавление в обертку слайдера синего цвета сам слайдер




      // создание обертки для слайдеров
      JPanel colorSlidersWrapper = new JPanel();

      // добавление в обертку слайдеров объект отображения текущего цвета
      colorSlidersWrapper.add(currentColorPanel);

      // добавление в обертку слайдеров обертку слайдера красного цвета
      colorSlidersWrapper.add(redColorsSliderWrapper);
      // добавление в обертку слайдеров обертку слайдера зеленого цвета
      colorSlidersWrapper.add(greenColorsSliderWrapper);
      // добавление в обертку слайдеров обретку слайдера синего цвета
      colorSlidersWrapper.add(blueColorsSliderWrapper);


      // добавление обертки слайдеров в окно
      frame.add(colorSlidersWrapper);


      JPanel panel = new PaintPanel(); // создание панели
      frame.add(panel); // Добавление панели

      frame.pack();
      frame.setVisible(true); // установка отображения окна
   }



   private PaintPanel() {
      setBackground(BACK_COLOR); // установка цвета заднего фона
      setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT)); // задание размера

      MyMouseHandler handler = new MyMouseHandler(); // получение объекта прослушивателя событий мыши

      this.addMouseListener(handler); // добавление прослушивателя событий мыши
      this.addMouseMotionListener(handler);
   }

   private class MyMouseHandler extends MouseAdapter {
      public void mousePressed(MouseEvent e) { // при нажатии левой кнопкой мыши

         System.out.println(LineColor);
         if(x1 == 0 && y1 == 0){ // если первая точка равна нулю
            x1 = e.getX(); // получение первой точки
            y1 = e.getY();
         }
         else{
            x2 = e.getX(); // получение второй точки
            y2 = e.getY();
            g = getGraphics(); // получение объекта графики

            g.setColor(LineColor); // установка цвета линии

            g.drawLine(x1, y1, x2, y2); // рисование линни по двум точкам

            x1 = x2; // изменение координат первой точки
            y1 = y2;
         }
      }
   }}