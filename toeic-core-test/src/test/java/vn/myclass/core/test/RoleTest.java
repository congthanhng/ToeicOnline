package vn.myclass.core.test;

import org.testng.annotations.Test;
import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.persistence.entity.RoleEntity;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

import static vn.myclass.core.common.constant.CoreConstant.SORT_DESC;

public class RoleTest {
    @Test
    public void checkFindAll(){
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list= roleDao.findAll();
    }

    @Test
    public void checkUpdateRole(){
        RoleDao roleDao=new RoleDaoImpl();
        RoleEntity entity=new RoleEntity();
        entity.setRoleId(1);
        entity.setName("MANEGER");
        roleDao.update(entity);
    }

    @Test
    public void checkSaveRole(){
        RoleDao roleDao=new RoleDaoImpl();
        RoleEntity entity=new RoleEntity();
        entity.setRoleId(2);
        entity.setName("VIEWER");
        roleDao.update(entity);
    }

    @Test
    public void checkFindById(){
        RoleDao roleDao=new RoleDaoImpl();
        RoleEntity entity=roleDao.findById(1);
    }

    @Test
    public void checkFindByProperty(){
        RoleDao roleDao=new RoleDaoImpl();
        String property=null;
        Object value=null;
        String sortExpresstion=null;
        String sortDirection=null;

//        property = "name";
//        value = "ADMIN";
//        sortDirection = "roleid";
//        sortDirection = SORT_DESC;
        Object[] objects= roleDao.findByProperty(property,value,sortExpresstion,sortDirection);
    }
    @Test
    public void checkDelete(){
        List<Integer> listId= new ArrayList<Integer>();
        listId.add(1);
        listId.add(2);
        RoleDao roleDao=new RoleDaoImpl();
        Integer count=roleDao.delete(listId);
    }
}
