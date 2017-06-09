package com.payulatam.prototipo;



import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Radio;
 
public class MainPage extends GenericForwardComposer {
 private static final long serialVersionUID = 4084521215385235831L;
  
 protected Radio rDeveloper;
 protected Radio rManager;
 protected Radio rAdministrator;
  
 private int getSelectedRadio(){
  if (rDeveloper.isChecked())
   return 0;
  if (rManager.isChecked())
   return 1;
  if (rAdministrator.isChecked())
   return 2;
  return -1;  
 }
  
 public void onClick$btnNotify(Event ent){
  int rgIndex = getSelectedRadio();
  switch (rgIndex){
  case 0:
   alert("Developer is notified");
   break;
  case 1:
   alert("Manager is notified");
   break;
  case 2:
   alert("Administrator is notified");
   break;
  default:
   alert("Please select person to notify");
  }  
 }
  
}