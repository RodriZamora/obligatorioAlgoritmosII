/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

public interface ICola<T> {
    void encolar(T dato);

    T desencolar();

    T front();

    boolean esVacia();

    T largo();
}

