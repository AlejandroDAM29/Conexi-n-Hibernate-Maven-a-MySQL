import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		
		System.out.println("INICIO DEL PROGRAMA...");
		
		//Para indicar que queremos usar Hibernate definimos las interfaces
		Configuration cfg =new Configuration().configure();
		//única instancia de sessionFactory en nuestra sesión
		SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
		Session session = sessionFactory.openSession();
		System.out.println("CONFIGURACIÓN REALIZADA");
		
		//Para insertar datos en la BD vamos a usar transactions
		Transaction tx = session.beginTransaction();
		Alumno a = new Alumno();
		a.setNombre("Alberto");
		a.setEdad(18);
		session.save(a);
		
		tx.commit(); //Hacemos el commit de la transacción y habremos insertado
					 // los datos del primer registro de alumno
		
		
		//Recueperar los datos de la BD
		tx = session.beginTransaction();
	
		Query query = session.createQuery("FROM Alumno");
		List list =  query.list();
		for(int i =0; i<list.size(); i++)
		{
			System.out.println(list.get(i).toString());
		}
		
		session.close();
		sessionFactory.close();
	}

}
