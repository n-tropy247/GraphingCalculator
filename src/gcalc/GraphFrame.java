/*
 * Copyright (C) 2019 Ryan Castelli
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

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Graphing functions.
 *
 * @author NTropy
 * @since 10/16/18
 * @version 9.19.2019
 */
final class GraphFrame extends JPanel {

    /**
     * Number of points to evaluate at. Bigger number will result in a more
     * precise graph.
     */
    private final int graphPts = 40;

    /**
     * Graphics handler.
     */
    private final BackgroundPanel bgp = new BackgroundPanel();

    /**
     * Graph constraints.
     */
    private final int axisConv = 10, xStep = 4, yStep = 3, axisLen = 5;

    /**
     * Window constraints.
     */
    private final int winH = 900, winW = 1400;

    /**
     * Arrays for plot points.
     */
    private final int[] x = new int[graphPts], y = new int[graphPts];

    /**
     * Constructor points to initialization method.
     */
    GraphFrame() {
        init();
    }

    /**
     * Settings for JFrame.
     */
    private void init() {
        requestFocus();
        setPreferredSize(new Dimension(winW, winH));
        bgp.setPreferredSize(new Dimension(winW, winH));
        add(bgp);
        genGraph();
        bgp.setWinConstraints(winW, winH);
        bgp.setArrays(x, y);
        bgp.setGraphConstraints(xStep, yStep, axisConv, axisLen, graphPts);
        bgp.repaint();
    }

    /**
     * Creates x and y coordinates for graph lines.
     */
    private void genGraph() {
        for (int j = 0; j < graphPts; j++) {
            x[j] = axisConv * j + winW / 2 + axisConv
                    - graphPts * axisConv / 2;
            y[j] = winH / 2
                    - func(j - graphPts / 2);
        }
    }

    /**
     * Function to graph.
     *
     * @param x x-value
     *
     * @return result of function
     */
    private static int func(final int x) {
        return (int) (Math.pow(x, 2) + x + 1);
    }
}
