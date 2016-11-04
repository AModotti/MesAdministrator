package ide.edit;

import javax.swing.table.DefaultTableModel;

public class EditVerifierForType {
	
	DefaultTableModel edittablemodel;
	int i;
	EditTable tblmodificarecords;
	
	public EditVerifierForType(DefaultTableModel edittablemodel,int i,EditTable tblmodificarecords){
		
		this.edittablemodel = edittablemodel;
		this.i = i;
		this.tblmodificarecords = tblmodificarecords;
		
	}

	public int verify() {
		
		int ret;
		String value4 = edittablemodel.getValueAt(i, 4).toString();
		String value5 = edittablemodel.getValueAt(i, 5).toString();
		String value6 = edittablemodel.getValueAt(i, 6).toString();
		String value7 = edittablemodel.getValueAt(i, 7).toString();
		String value8 = edittablemodel.getValueAt(i, 8).toString();
		String value9 = edittablemodel.getValueAt(i, 9).toString();
		String value10 = edittablemodel.getValueAt(i, 10).toString();
		
		if(!value4.trim().toUpperCase().equals("")){
			if((value4.trim().toUpperCase().equals("ING"))||(value4.trim().toUpperCase().equals("USC"))){
				if(value5.trim().equals("")){
					ret = 0;
				}else{
					tblmodificarecords.getModel().setValueAt("", i, 5);
					ret = 1;
				}
				if(value6.trim().equals("")){
					ret = 1;
				}else {
					ret = 0;
				}
				if(value7.trim().equals("")){
					ret = 1;
				}else{
					ret = 0;
				} 
				if(value8.trim().equals("")){
					ret = 1;
				}else{
					ret = 0;
				}
				if(value9.trim().equals("0")){
					ret = 0;
				}else{
					tblmodificarecords.getModel().setValueAt("0", i, 9);
					ret = 1;
				}
				if(value10.trim().equals("0")){
					ret = 0;
				}else{
					tblmodificarecords.getModel().setValueAt("0", i, 10);
					ret = 1;
				}
			}else if((value4.trim().toUpperCase().equals("ATT"))||(value4.trim().toUpperCase().equals("LAV"))){
				if(value5.trim().equals("")){
					ret = 1;
				}else{
					ret = 0;
				}
				if(value6.trim().equals("")){
					ret = 1;
				}else{
					ret = 0;
				} 
				if(value7.trim().equals("")){
					ret = 1;
				}else{
					ret = 0;
				} 
				if(value8.trim().equals("")){
					ret = 1;
				}else{
					ret = 0;
				}
				if(value9.trim().equals("0")){
					ret = 1;
				}else{
					ret = 0;
				}
				if(value10.trim().equals("0")){
					ret = 1;
				}else{
					ret = 0;
				}
			}else{
				ret = 1;
			}
		}else{
			ret = 1;
		}
		
		return ret;
	}

}
