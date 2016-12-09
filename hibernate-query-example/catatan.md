## Hibernate Query Language

**Delete**
```
String hql = "DELETE FROM Employee WHERE id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("employee_id", 10);
int result = query.executeUpdate();
```

**Update**
```
String hql = "UPDATE Employee set salary = :salary WHERE id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("salary", 1000);
query.setParameter("employee_id", 10);
int result = query.executeUpdate();
```

**Positional parameters**

```
Query query = session.createQuery("from Student where studentId = ? and studentName=?");
query.setInteger(0, 1).setString(1, "Jack");
```

**Select**
```
String hql = "FROM Employee";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "FROM com.hibernatebook.criteria.Employee";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "FROM Employee AS E";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "FROM Employee E";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "from Category";
Query query = session.createQuery(hql);
List<Category> listCategories = query.list();

String hql = "from Product where category.name = 'Computer'";
Query query = session.createQuery(hql);
List<Product> listProducts = query.list();

String hql = "from Product where description like :keyword";
String keyword = "New";
Query query = session.createQuery(hql);
query.setParameter("keyword", "%" + keyword + "%");
List<Product> listProducts = query.list();

Query query = session.createQuery("from Karyawan where id = :id");
query.setLong("id", id);
Karyawan karyawan = (Karyawan) query.uniqueResult();

from Product where price >= 500 and price <= 1000
```

**Aggregate Methods**

*avg
*count
*max
*min
*sum
*count(distinct…)

```
Query query = session.createQuery("select max(gaji) from Karyawan");
BigDecimal max = (BigDecimal) query.uniqueResult();

String hql = "select count(name) from Product";
Query query = session.createQuery(hql);
List listResult = query.list();
Number number = (Number) listResult.get(0);
System.out.println(number.intValue());
```

**Join Query**

*inner join
*left outer join
*right outer join
*full join

```
String hql = "from Product p inner join p.category";
Query query = session.createQuery(hql);
List<Object[]> listResult = query.list();
for (Object[] aRow : listResult) {
    Product product = (Product) aRow[0];
    Category category = (Category) aRow[1];
    System.out.println(product.getName() + " - " + category.getName());
}

from Product p inner join p.category with p.price > 500
```

**Sort Query**

```
String hql = "from Product order by price ASC";
Query query = session.createQuery(hql);
List<Product> listProducts = query.list()
```

**Group By Query**

```
String hql = "select sum(p.price), p.category.name from Product p group by category";
Query query = session.createQuery(hql);
List<Object[]> listResult = query.list();
for (Object[] aRow : listResult) {
    Double sum = (Double) aRow[0];
    String category = (String) aRow[1];
    System.out.println(category + " - " + sum);
}
```

**Pagination Query**

```
String hql = "from Product";
Query query = session.createQuery(hql);
query.setFirstResult(0);
query.setMaxResults(10);
List<Product> listProducts = query.list();
```

**Date Range Query**

```
String hql = "from Order where purchaseDate >= :beginDate and purchaseDate <= :endDate";
Query query = session.createQuery(hql);
SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
Date beginDate = dateFormatter.parse("2016-11-01");
query.setParameter("beginDate", beginDate);
Date endDate = dateFormatter.parse("2016-11-22");
query.setParameter("endDate", endDate);
List<Order> listOrders = query.list();
```
