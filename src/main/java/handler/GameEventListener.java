package handler;

public interface GameEventListener {
    default void onBombReveal(){
    };

    default void onUnflag(){
    };

    default void onFlag(){
    };

    default void onFreeCellReveal(){
    };
}
