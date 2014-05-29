/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.life.service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.epam.life.model.Cell;
import org.epam.life.model.Template;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class Logic implements LogicService {

    private int maxCols;
    private int maxRows;
    private int generation;

    private List<LogicCell> currentStep = new ArrayList<>();
    private HashMap<LogicCell, LogicCell> nextStep = new HashMap<>();

    public Logic(int maxCols, int maxRows) {
        this.maxCols = maxCols;
        this.maxRows = maxRows;
    }

    public int getMaxCols() {
        return maxCols;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getGeneration() {
        return generation;
    }

    public void setMaxCols(int maxCols) {
        this.maxCols = maxCols;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    @Override
    public Template nextStep() {
        List<Cell> t = new ArrayList<>();
        for (LogicCell lc : next()) {
            t.add(lc);
        }
        Template template = new Template();
        template.setCells(t);
        return template;
    }

    private List<LogicCell> next() {
        List<LogicCell> resultList = new ArrayList<>();
        for (LogicCell cell : currentStep) {
            cell.neigbour = 0;
            nextStep.put(cell, cell);
        }
        for (LogicCell c : currentStep) {
            addNeighbors(c);
        }
        for (LogicCell cell : currentStep) {
            if (cell.neigbour == 2 || cell.neigbour == 3) {
                resultList.add(cell);
            }
        }

        for (LogicCell c : nextStep.keySet()) {
            if (!resultList.contains(c)) {
                if (c.neigbour == 3) {
                    resultList.add(c);
                }
            }
        }
        nextStep.clear();
        currentStep = resultList;
        generation++;
        return resultList;
    }

    private void addNeighbors(Cell c) {
        int x = c.getX();
        int y = c.getY();
        addNeighbour(x - 1, y - 1, c);
        addNeighbour(x, y - 1, c);
        addNeighbour(x + 1, y - 1, c);
        addNeighbour(x - 1, y, c);
        addNeighbour(x + 1, y, c);
        addNeighbour(x - 1, y + 1, c);
        addNeighbour(x, y + 1, c);
        addNeighbour(x + 1, y + 1, c);

    }

    private void addNeighbour(int x, int y, Cell cell) {
        if (x >= 0 && x < maxCols && y >= 0 && y < maxRows) {
            LogicCell c = new LogicCell(x, y);
            if (!nextStep.containsKey(c)) {
                c.neigbour = 1;
                nextStep.put(c, c);
            } else {
                nextStep.get(c).neigbour++;
            }
        }
    }

    @Override
    public Template getGameState() {
        List<Cell> t = new ArrayList<>();
        for (LogicCell lc : currentStep) {
            t.add(lc);
        }
        Template template = new Template();
        template.setCells(t);

        return template;
    }

    @Override
    public void loadTemplate(Template t) {
        for (Cell c : t.getCells()) {
            currentStep.add(new LogicCell(c.getX(), c.getY()));
        }
    }

    private class LogicCell extends Cell {

        private int neigbour;
        private final int HASHFACTOR = 8197;

        LogicCell(int x, int y) {
            super.setX(x);
            super.setY(y);
        }

        @Override
        public int hashCode() {
            return HASHFACTOR * super.getX() + super.getY();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Cell other = (Cell) obj;
            if (super.getX() != other.getX()) {
                return false;
            }
            if (super.getY() != other.getY()) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Cell{" + "x=" + super.getX() + ", y=" + super.getY()
                    + ", neigbour=" + neigbour + '}';
        }
    }

}
