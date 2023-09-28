/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchlog.business;

import lab4.searchlog.persistence.searchCRUD;
/**
 *
 * @author student
 */
public class SearchLog {
    
    public void addSearch(int movieid, String username){
        searchCRUD.insertSearch(movieid, username);
    }
}
