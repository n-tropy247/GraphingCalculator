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

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Executes graphing calculator.
 *
 * @author NTropy
 * @since 10/16/18
 * @version 11.19.2023
 */
final class Calculator extends JFrame {

    /**
     * Default constructor.
     */
    Calculator() {
        init();
    }

    /**
     * Adds {@link gcalc.CalculatorFrame CalculatorFrame}
     *  and initializes application view.
     */
    private void init() {
        getContentPane().add(new CalculatorFrame());
        setResizable(false);
        pack();
        setTitle("Graphing Calculator");
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Adds utility to event queue to be run.
     *
     * @param args command-line arguments; unused here
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Calculator();
            ex.setVisible(true);
            ex.requestFocus();
        });
    }
}
