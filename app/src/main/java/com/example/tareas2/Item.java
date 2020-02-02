package com.example.tareas2;

import androidx.lifecycle.LiveData;

import java.io.Serializable;

public class Item extends LiveData implements Serializable {
	private String title;
	private String subtitle;
	private boolean active;
	private Observador observador;

	Item(String title, String subtitle, boolean active) {
		this.title = title;
		this.subtitle = subtitle;
		this.active = active;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public boolean isActive() {
		return active;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void notificarCambio(){
		if(this.observador!=null){
			observador.call();
		}
	}

	public void setObservador(Observador observador){
		this.observador = observador;
	}

}
