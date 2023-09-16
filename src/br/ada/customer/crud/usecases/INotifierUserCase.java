package br.ada.customer.crud.usecases;

//notificação por email ou sms
public interface INotifierUserCase<T> {

    void registered(T object);

    void updated(T object);

    void deleted(T object);

}
