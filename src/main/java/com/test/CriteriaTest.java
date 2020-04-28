package com.test;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;

public class CriteriaTest {
    public void saveCar(Session session){
        Transaction tr=session.beginTransaction();
        CarEntity carEntity=new CarEntity();
        carEntity.setId(200);
        carEntity.setName("mvn");
        session.save(carEntity);
        tr.commit();
    }
    public static  void  main(String[] args){
        Configuration configuration=new Configuration().configure();
        SessionFactory sessionFactory=configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();



       //条件
        Criteria criteria=session.createCriteria(CarEntity.class);
        List list=criteria.list();

        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            CarEntity c1=(CarEntity)iterator.next();
            System.out.println(c1.getId() + "," + c1.getName());
        }



        Transaction transaction = session.beginTransaction();

       CarEntity carEntity = new CarEntity();
       carEntity.setName("mvn");
      carEntity.setId(400);

     session.save(carEntity);


         //绑定变量
          //insert into（？，？）；
      String string="update  CarEntity set name= 'Criteria' where id =:id" ;
    Query query=session.createQuery(string);
    query.setParameter("id",200);
    query.executeUpdate();//适用于insert,delete,update

        transaction.commit();
        session.clear();

        criteria=session.createCriteria(CarEntity.class);
        criteria.add(Restrictions.eq("id",300));
//        criteria.add(Restrictions.like("name","mvn"));
        list=criteria.list();

        iterator=list.iterator();
        while (iterator.hasNext()){
            CarEntity c1=(CarEntity)iterator.next();
            System.out.println(c1.getId() + "," + c1.getName());
        }
    }
}
