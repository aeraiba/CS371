class KV {
	public Object key;
	public Object val;
    public KV next;

    public KV(Object key, Object value) {
		this.key = key;
		this.val = value;
	}

	public Object getKey() {
		return this.key;
	}
	public Object getValue(){
		return this.val;
	}

}
