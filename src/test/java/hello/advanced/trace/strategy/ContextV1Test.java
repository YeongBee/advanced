package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        //  비지니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //  비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("result = {}ms", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //  비지니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //  비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("result = {}ms", resultTime);
    }

    /**
     *전략 패턴 적용
     */
    @Test
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new StrategyLogic1();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }


    @Test
    void strategyV2(){
        Strategy strategy1 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직 실행 1");
            }
        };

        ContextV1 contextV1 = new ContextV1(strategy1);
        contextV1.execute();
        Strategy strategy2 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직 실행 1");
            }
        };

        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }

    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1( new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 실행 1");
            }
        });
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 실행 1");
            }
        });

        contextV2.execute();
    }
    @Test
    void strategyV4(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직 실행 1"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직 실행 1"));
        contextV2.execute();
    }
}
