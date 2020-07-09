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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Graphing functions.
 *
 * @author NTropy
 * @since 10/16/18
 * @version 7.1.2020
 */
final class CalculatorFrame extends JPanel {

    /**
     * Graphics handler.
     */
    private final GraphingPanel bgp = new GraphingPanel();

    /**
     * Number of points to evaluate at. Bigger number will result in a more
     * precise graph.
     */
    private final int graphPts = 40;

    /**
     * Length of user input box.
     */
    private final int usrInLen = 15;

    /**
     * Graph constraints.
     */
    private final int axisConv = 10, xStep = 4, yStep = 3, axisLen = 5;

    /**
     * Window constraints.
     */
    private final int winH = 900, winW = 1400, graphH = 700, graphW = 1400;

    /**
     * Arrays for plot points.
     */
    private final int[] x = new int[graphPts], y = new int[graphPts];

    /**
     * Current scaling on graph.
     */
    private int xScale = 1, yScale = 1;

    /**
     * Button to submit user input and redraw.
     */
    private JButton redrawBtn;

    /**
     * User input fields.
     */
    private JTextField inputXScale, inputYScale;

    /**
     * Constructor points to initialization method.
     */
    CalculatorFrame() {
        init();
    }

    /**
     * Settings for JFrame.
     */
    private void init() {
        final UserHandler handler = new UserHandler();
        requestFocus();

        setPreferredSize(new Dimension(winW, winH));
        setLayout(new BorderLayout());

        bgp.setPreferredSize(new Dimension(graphW, graphH));
        add(bgp, BorderLayout.PAGE_START);

        redrawBtn = new JButton("Submit");
        redrawBtn.addActionListener(handler);

        //initialize user input fields
        inputXScale = new JTextField("x-scale", usrInLen);
        inputXScale.addFocusListener(handler);
        inputYScale = new JTextField("y-scale", usrInLen);
        inputYScale.addFocusListener(handler);

        JPanel userInput = new JPanel();
        userInput.setLayout(new FlowLayout());

        userInput.add(inputXScale);
        userInput.add(inputYScale);
        userInput.add(redrawBtn);

        add(userInput, BorderLayout.PAGE_END);

        genGraph();

        bgp.setWinConstraints(graphW, graphH);
        bgp.setArrays(x, y);
        bgp.setGraphConstraints(xStep, yStep, axisConv, axisLen, graphPts);
        bgp.repaint();
    }

    /**
     * Creates x and y coordinates for graph lines.
     */
    private void genGraph() {
        for (int j = 0; j < graphPts; j++) {
            x[j] = axisConv * xScale * j + graphW / 2 + axisConv
                    - graphPts * xScale * axisConv / 2;
            y[j] = graphH / 2 - yScale * func(j - graphPts / 2);
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

    /**
     * Handler for user interaction with application.
     */
    private class UserHandler implements ActionListener, FocusListener {

        /**
         * Text that was originally stored in the field.
         */
        private String origText;

        /**
         * Blank out a field when focus gained.
         *
         * @param fe focus event sent by field
         */
        @Override
        public void focusGained(final FocusEvent fe) {
            origText = ((JTextField) fe.getSource()).getText();
            ((JTextField) fe.getSource()).setText("");
        }

        /**
         * Necessary override.
         *
         * @param fe focus event sent by field
         */
        @Override
        public void focusLost(final FocusEvent fe) {
            if (((JTextField) fe.getSource()).getText().isEmpty()) {
                ((JTextField) fe.getSource()).setText(origText);
            }
        }

        /**
         * Handle button press event.
         *
         * @param ae action event sent by button
         */
        @Override
        public void actionPerformed(final ActionEvent ae) {
            try {
                CalculatorFrame.this.xScale = Integer.parseInt(
                        CalculatorFrame.this.inputXScale.getText());
            } catch (NumberFormatException nfe) {
                System.err.println(nfe);
                CalculatorFrame.this.inputXScale.setText("NaN");
                CalculatorFrame.this.xScale = 1;
            }
            try {
                CalculatorFrame.this.yScale = Integer.parseInt(
                        CalculatorFrame.this.inputYScale.getText());
                genGraph();
                bgp.repaint();
            } catch (NumberFormatException nfe) {
                System.err.println(nfe);
                CalculatorFrame.this.inputYScale.setText("NaN");
                CalculatorFrame.this.yScale = 1;
            }
        }
    }
}
