package com.racofix.basic.pattern.observer;

public class ObserverExampleTest {

    public static void main(String[] args) {

        Subscriber<MagazineBo> observer1 = new Subscriber<MagazineBo>() {
            @Override
            public void onUpdate(EventBus observable, MagazineBo item) {
                System.out.print(item.toString());
            }
        };

        Subscriber<MagazineBo> observer2 = new Subscriber<MagazineBo>() {
            @Override
            public void onUpdate(EventBus observable, MagazineBo item) {
                System.out.print(item.toString());
            }
        };

        EventBus.getDefault().register(observer1);
        EventBus.getDefault().register(observer2);

        MagazineBo magazineBo1 = new MagazineBo("新西兰海底惊险'巨型'蠕虫, 长约8米移动缓慢");
        EventBus.getDefault().notifyObservers(magazineBo1);

        MagazineBo magazineBo2 = new MagazineBo("知乎全网最高赞: 人生最重要的是能力竟是'憋大招'");
        EventBus.getDefault().notifyObservers(magazineBo2);
    }
}
