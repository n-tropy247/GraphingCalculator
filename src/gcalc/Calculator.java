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

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Executes graphing calculator.
 * @author NTropy
 * @since 10/16/18
 * @version 9.19.2019
 */
final class Calculator extends JFrame {

    /**
     * Initializes calculator.
     */
    Calculator() {
        init();
    }

    /**
     * Initialize settings.
     */
    private void init() {
        getContentPane().add(new GraphFrame());
        setResizable(false);
        pack();
        setTitle("Graphing Calculator");
        setLocationByPlatform(true);
        requestFocus();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Invokes utility.
     *
     * @param args command-line arguments; unused here
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Calculator();
            ex.setVisible(true);
        });
    }
}
