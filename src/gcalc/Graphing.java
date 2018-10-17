/*
 * Copyright (C) 2018 Ryan Castelli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gcalc;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;

import javax.swing.JFrame;

/**
 * Graphing functions.
 *
 * @author NTropy
 * @version 10/16/18
 */
final class Graphing extends JFrame {

    /**
     * Graphics handler.
     */
    private static final BackGroundPanel BACKGROUND_PANEL
            = new BackGroundPanel();

    /**
     * Length of axis markers from center point.
     */
    private static final int AXIS_LEN = 5;

    /**
     * Number of points to evaluate at. Bigger number will result in a more
     * precise graph.
     */
    private static final int GRAPH_POINTS = 40;

    /**
     * Conversion factor of x-points.
     */
    private static final int X_CONV = 10;

    /**
     * Step for each x-axis label.
     */
    private static final int X_STEP = 4;

    /**
     * Step for each y-axis label.
     */
    private static final int Y_STEP = 3;

    /**
     * Size of window.
     *
     * WINDOW_H window height WINDOW_W window width
     */
    private static final int WINDOW_H = 900, WINDOW_W = 1400;

    /**
     * Arrays for plot points.
     */
    private static final int[] X1 = new int[GRAPH_POINTS],
            X2 = new int[GRAPH_POINTS], Y1 = new int[GRAPH_POINTS],
            Y2 = new int[GRAPH_POINTS];

    /**
     * Main window frame.
     */
    private static JFrame frame;

    /**
     * Constructor points to initialization method.
     */
    private Graphing() {
        init();
    }

    /**
     * Creates JFrame thread.
     *
     * @param args command-line arguments, unused here
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            frame = new Graphing();
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }

    /**
     * Settings for JFrame.
     */
    private void init() {
        requestFocus();
        setSize(WINDOW_W, WINDOW_H);
        BACKGROUND_PANEL.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        add(BACKGROUND_PANEL, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        graph();
    }

    /**
     * Creates x and y coordinates for graph lines.
     */
    private void graph() {
        int[] xCoords = new int[GRAPH_POINTS];
        int[] yCoords = new int[GRAPH_POINTS];

        int loopNum = 0;
        for (int j = 0; j < GRAPH_POINTS * 2; j++) {
            if (j % 2 == 0) {
                xCoords[j - loopNum] = X_CONV * j + WINDOW_W / 2 + X_CONV
                        - GRAPH_POINTS * X_CONV;
            } else {
                loopNum++;
                yCoords[j - loopNum] = WINDOW_H / 2
                        - func(j - GRAPH_POINTS / 2 - loopNum);
            }
        }

        for (int i = 0; i < xCoords.length - 1; i++) {
            X1[i] = xCoords[i];
            X2[i] = xCoords[i + 1];
            Y1[i] = yCoords[i];
            Y2[i] = yCoords[i + 1];

            BACKGROUND_PANEL.repaint();
        }
    }

    /**
     * Function to graph.
     *
     * @param x x-value
     * @return result of function
     */
    private static int func(final int x) {
        return (int) (Math.pow(x, 2) + x + 1);
    }

    /**
     * Graphics for frame.
     */
    static final class BackGroundPanel extends Panel {

        /**
         * Constructor, refers to parent class.
         */
        private BackGroundPanel() {
            super();
        }

        /**
         * Draws in lines and points.
         *
         * @param g graphics of panel
         */
        @Override
        public void paint(final Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawLine(0, WINDOW_H / 2, WINDOW_W, WINDOW_H / 2); //x-axis
            for (int j = 0; j < WINDOW_W; j += X_CONV * X_STEP) {
                g2.drawLine(j, WINDOW_H / 2 - AXIS_LEN, j, WINDOW_H / 2
                        + AXIS_LEN);
                if (j < WINDOW_W / 2) {
                    g2.drawString(String.valueOf((j - WINDOW_W / 2 - X_CONV)
                            - X_CONV), j - X_CONV / 2, WINDOW_H / 2 + AXIS_LEN
                                    + X_CONV * 2);
                } else {
                    g2.drawString(String.valueOf((j - WINDOW_W / 2 + X_CONV)
                            + X_CONV), j - X_CONV / 2, WINDOW_H / 2 + AXIS_LEN
                                    + X_CONV * 2);
                }
            }
            g2.drawLine(WINDOW_W / 2, 0, WINDOW_W / 2, WINDOW_H); //y-axis
            for (int j = 0; j < WINDOW_H; j += X_CONV * Y_STEP) {
                g2.drawLine(WINDOW_W / 2 - AXIS_LEN, j, WINDOW_W / 2
                        + AXIS_LEN, j);
                if (j <= WINDOW_H / 2) {
                    g2.drawString(String.valueOf(-(j - WINDOW_H / 2
                            - (X_STEP * X_CONV)) - X_CONV), WINDOW_W / 2
                                    + AXIS_LEN + X_CONV * 2, j - X_CONV * 2
                                            - (X_CONV / 2));
                } else {
                    g2.drawString(String.valueOf(-(j - WINDOW_H / 2
                            + (X_STEP * X_CONV)) + X_CONV), WINDOW_W / 2
                                    + AXIS_LEN + X_CONV * 2, j + X_CONV * Y_STEP
                                            + (X_CONV / 2));
                }
            }
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2));
            for (int j = 0; j < GRAPH_POINTS; j++) {
                g2.drawLine(X1[j], Y1[j], X2[j], Y2[j]);
            }
        }
    }
}
