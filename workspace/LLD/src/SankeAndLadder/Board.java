package SankeAndLadder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Board {
    private int totalRows;
    private int totalCols;
    private Set<SpecialElement> specialElements;
    private int destinationPoint;

    public Board(int totalRows, int totalCols) {
        this.totalRows = totalRows;
        this.totalCols = totalCols;
        this.destinationPoint = totalRows * totalCols;
        this.specialElements = new HashSet<>();
    }

    public Set<SpecialElement> getSpecialElements() {
        return specialElements;
    }

    public void setSpecialElements(Set<SpecialElement> specialElements) {
        this.specialElements = specialElements;
    }

    public void addSpecialElement(SpecialElement specialElement){
        this.specialElements.add(specialElement);
    }

    public int getDestinationPoint() {
        return destinationPoint;
    }

    public boolean isSpecialElementPresent(int position){
        long count = specialElements.stream().filter(elem -> elem.getStart() == position).count();
        return count>0;
    }

    public int getSpecialElementEndPosition(int start){
        if(isSpecialElementPresent(start)){
            Optional<SpecialElement> first = specialElements.stream().filter(elem -> elem.getStart() == start).findFirst();
            if(first.isPresent()){
                SpecialElement specialElement = first.get();
                System.out.println("Special Element Type "+specialElement.getClass().getName());
                System.out.println("Start Position "+specialElement.getStart()+"\tEnd Position "+specialElement.getEnd());
                return specialElement.getEnd();
            }
        }
        return -1;
    }
}
