package com.iteratrlearning.shu_book.chapter_05;

@FunctionalInterface
interface Rule {
    void perform(Facts facts);
}

public class DefaultRule implements Rule{

    private final Condition condition;
    private final Action action;

    public DefaultRule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void perform(Facts facts) {
        if(condition.evalute(facts)){
            action.perform(facts);
        }
    }
}