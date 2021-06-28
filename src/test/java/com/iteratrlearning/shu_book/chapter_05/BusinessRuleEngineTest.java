package com.iteratrlearning.shu_book.chapter_05;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessRuleEngineTest {
    @Test
    public void shouldHaveNoRulesInitially(){
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddTwoActions(){
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addRule(facts -> {
            final String jobTitle = facts.getFacts("jobTitle");
            if("CEO".equals(jobTitle)){
                final String name = facts.getFacts("name");
                System.out.println(name);
            }
        });
        businessRuleEngine.addRule(facts -> {
            var jobTitle = facts.getFacts("jobTitle");
            if("CEO".equals(jobTitle)){
                var name = facts.getFacts("name");
                System.out.println(name);
            }
        });

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldExecuteOneAction(){
        //given
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        final Rule ruleSendEmailToSaleWhenCDO = RuleBuilder
                .when(facts -> "CEO".equals(facts.getFacts("jobTitle")))
                .then(facts -> {
                    var name = facts.getFacts("name");
                    System.out.println("name");
                });

        //when
        businessRuleEngine.addRule(ruleSendEmailToSaleWhenCDO);
        businessRuleEngine.run();

        //then
        verify(ruleSendEmailToSaleWhenCDO).perform(mockFacts);
    }
    @Test
    public void calcForecastedAmountWithFacts(){
        //given
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        //when
        businessRuleEngine.addRule(facts -> {
            var forecastedAmount = 0.0;
            var dealStage = Stage.valueOf(facts.getFacts("stage"));
            var amount = Double.parseDouble(facts.getFacts("amount"));
           /* var forecastedAmount = amount * switch (dealStage){
                case LEAD -> 0.2;
                case EVALUATING -> 0.5;
                case INTERESTED -> 0.8;
                case CLOSE -> 1;
            }*/
            facts.addFacts("forecastedAmount", String.valueOf(forecastedAmount));
        });

        //then
        verify(mockAction).perform(mockFacts);

    }


    @Test
    public void shouldPerformWithDomain(){
        //given
        final Condition condition = (Facts facts) -> "CEO".equals(facts.getFacts("jobTitle"));
        final Action action  = (Facts facts) -> {
            var name = facts.getFacts("name");
            System.out.println(name);
        };

        final Rule rule = new DefaultRule(condition, action);

        //then
        //erify(mockAction).perform(mockFacts);

    }
}
