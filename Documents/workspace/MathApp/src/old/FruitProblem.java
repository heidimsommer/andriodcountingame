//package old;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import android.widget.Button;
//
//
//
//
//public class FruitProblem {
//
//	private Picture picture1;
//	private Picture picture2;
//	int[] allOptions = new int[3];
//	ArrayList<ButtonCombo> allButtonCombos;
//
//	public FruitProblem() {
//	}
//
//	public FruitProblem(Picture picture1,Picture picture2) {
//		this.picture1 = picture1;
//		this.picture2 = picture2;
//		setOptions();
//	}
//
//	public void setPictures(Picture picture1, Picture picture2) {
//		this.picture1 = picture1;
//		this.picture2 = picture2;
//		setOptions();
//	}
//
//	public Picture getPicture1() {
//		return picture1;
//	}
//
//	public Picture getPicture2() {
//		return picture2;
//	}
//
//	public int[] getAllOptions() {
//		return allOptions;
//	}
//
//	public int getAnswer() {
//		int answer = picture1.getNumber() + picture1.getNumber();
//		return answer;
//	}
//
//	public void setAllButtonCombos(ArrayList<Button> allButtons){
//		int[] allOptions = getAllOptions();
//		allButtonCombos = new ArrayList<ButtonCombo>();
//
//		for(int i = 0; i < allOptions.length; i++){
//			Button button = allButtons.get(i);
//			button.setText(allOptions[i]);
//			ButtonCombo temp = new ButtonCombo(button, allOptions[i]);
//
//			allButtonCombos.add(temp);
//		}
//
//	}
//	
//	public ArrayList<ButtonCombo> getAllButtonCombos(){
//		return allButtonCombos;
//	}
//
//	public void setOptions(){
//		int answer = getAnswer();
//		int[] values = new int[3];
//		int n = 0;
//		while (n < 3) {
//			boolean alert = false;
//			int temp = (int)(Math.random() * 9);
//			if (temp == answer) {
//				alert = true;
//			}
//			for(int i = 0; i < values.length; i++){
//				if (temp == values[i]){
//					alert = true;
//				}
//			}
//			if (!alert){
//				values[n] = temp;
//				n++;
//			}
//		} 
//		values[4] = answer;
//		Collections.shuffle(Arrays.asList(values));
//
//		allOptions = values;
//	}
//
//
//
//
//}
