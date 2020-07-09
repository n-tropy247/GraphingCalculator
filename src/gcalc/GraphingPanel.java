/*
 * Copyright (C) 2020 Ryan Castelli
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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Graphics for Calculator Frame.
 *
 * @author NTropy
 * @since 10/16/18
 * @version 7.9.2020
 */
final class GraphingPanel extends JPanel {

    /**
     * Frame constraints.
     */
    private int winW, winH, xStep, yStep, axisConv, axisLen, graphPts;

    /**
     * Arrays for graph points.
     */
    private int[] x, y;

    /**
     * Constructor, refers to parent class.
     */
    GraphingPanel() {
        super();
    }

    /**
     * Set window constraints for graphing.
     *
     * @param w specified window width
     * @param h specified window height
     */
    public void setWinConstraints(final int w, final int h) {
        winW = w;
        winH = h;
    }

    /**
     * Set local arrays of points.
     *
     * @param xSpec specified x-vals
     * @param ySpec specified x-vals
     */
    public void setArrays(final int[] xSpec, final int[] ySpec) {
        x = xSpec;
        y = ySpec;
    }

    /**
     * Set constraints for drawing graph.
     *
     * @param xs step distance on x-axis
     * @param ys step distance on y-axis
     * @param ac conversion factor for axes
     * @param al length of axes
     * @param gp points in graph
     */
    public void setGraphConstraints(final int xs, final int ys, final int ac,
            final int al, final int gp) {
        xStep = xs;
        yStep = ys;
        axisConv = ac;
        axisLen = al;
        graphPts = gp;
    }

    /**
     * Draws in lines and points.
     *
     * @param g graphics of panel
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(0, winH / 2, winW, winH / 2); //x-axis
        for (int j = 0; j < winW; j += axisConv * xStep) {
            g2.drawLine(j, winH / 2 - axisLen, j, winH / 2
                    + axisLen);
            if (j < winW / 2) {
                g2.drawString(String.valueOf((j - winW / 2 - axisConv)
                        - axisConv), j - axisConv / 2, winH / 2 + axisLen
                        + axisConv * 2);
            } else {
                g2.drawString(String.valueOf((j - winW / 2 + axisConv)
                        + axisConv), j - axisConv / 2, winH / 2 + axisLen
                        + axisConv * 2);
            }
        }
        g2.drawLine(winW / 2, 0, winW / 2, winH); //y-axis
        for (int j = 0; j < winH; j += axisConv * yStep) {
            g2.drawLine(winW / 2 - axisLen, j, winW / 2
                    + axisLen, j);
            if (j <= winH / 2) {
                g2.drawString(String.valueOf(-(j - winH / 2
                        - (xStep * axisConv)) - axisConv), winW / 2
                        + axisLen + axisConv * 2, j - axisConv * 2
                        - (axisConv / 2));
            } else {
                g2.drawString(String.valueOf(-(j - winH / 2
                        + (xStep * axisConv)) + axisConv), winW / 2
                        + axisLen + axisConv * 2, j + axisConv * yStep
                        + (axisConv / 2));
            }
        }

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2));
        for (int j = 0; j < graphPts - 1; j++) {
            g2.drawLine(x[j], y[j], x[j + 1], y[j + 1]);
        }
    }
}
