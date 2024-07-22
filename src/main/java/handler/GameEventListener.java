package handler;

public interface GameEventListener {
    void onBombReveal();

    void onUnflag();

    void onFlag();

    void onFreeCellReveal();
}
