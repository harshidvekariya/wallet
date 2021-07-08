/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Currency;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CurrencyDao {
    @SuppressWarnings("unchecked")
    public List <Currency> getAllCurrency() {

        Transaction transaction = null;
        List <Currency> listOfCurrens = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfCurrens = session.createQuery("from Currency").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfCurrens;
    }
    
}
