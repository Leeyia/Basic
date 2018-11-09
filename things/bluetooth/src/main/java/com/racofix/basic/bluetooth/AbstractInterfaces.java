package com.racofix.basic.bluetooth;

public abstract class AbstractInterfaces {
    protected abstract void successful();

    public  interface Interfaces{
        void failure();
    }

    public static void main(String[] args){
        AbstractInterfaces abstractInterfaces = new AbstractInterfaces() {
            @Override
            protected void successful() {

            }
        };

        Interfaces interfaces = new Interfaces() {
            @Override
            public void failure() {

            }
        };
    }
}
