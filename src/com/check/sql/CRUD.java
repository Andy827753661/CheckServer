package com.check.sql;

public class CRUD {
	private MapFactory mapFactory;
	private BeanFactory beanFactory;
	private ModificationFactory modificationFactory;
	private AggregateFactory aggregateFactory;
	

	public CRUD() {
		this.mapFactory = new MapFactory();
		this.beanFactory = new BeanFactory();
		this.modificationFactory = new ModificationFactory();
		this.aggregateFactory = new AggregateFactory();
	}

	public MapFactory getMapFactory() {
		return mapFactory;
	}

	public void setMapFactory(MapFactory mapFactory) {
		this.mapFactory = mapFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public ModificationFactory getModificationFactory() {
		return modificationFactory;
	}

	public void setModificationFactory(ModificationFactory modificationFactory) {
		this.modificationFactory = modificationFactory;
	}

	public AggregateFactory getAggregateFactory() {
		return aggregateFactory;
	}

	public void setAggregateFactory(AggregateFactory aggregateFactory) {
		this.aggregateFactory = aggregateFactory;
	}
	
	
}
