package com.groupf.java.swing.m7.entity;

import org.json.JSONObject;

/**
 *
 * @author Baker
 */
public class Pedido {
    private int tableid;
    private JSONObject pedidojson;
    private boolean isServido;
    private boolean isPagado;

    public Pedido() {
    }

    public int getTableid() {
        return tableid;
    }

    public void setTableid(int tableid) {
        this.tableid = tableid;
    }

    public JSONObject getPedidojson() {
        return pedidojson;
    }

    public void setPedidoJson(JSONObject pedidojson) {
        this.pedidojson = pedidojson;
    }

    public boolean isServido() {
        return isServido;
    }

    public void setIsServido(boolean isServido) {
        this.isServido = isServido;
    }

    public boolean isPagado() {
        return isPagado;
    }

    public void setIsPagado(boolean isPagado) {
        this.isPagado = isPagado;
    }
}
