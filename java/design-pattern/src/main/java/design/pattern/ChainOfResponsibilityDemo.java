package design.pattern;

public class ChainOfResponsibilityDemo {
}

interface DispenseChainI {
    void setNextChain(DispenseChainI nextChain);
    void dispense();
}
