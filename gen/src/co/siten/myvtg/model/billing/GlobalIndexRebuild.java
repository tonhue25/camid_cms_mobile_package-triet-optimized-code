package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GLOBAL_INDEX_REBUILD database table.
 * 
 */
@Entity
@Table(name="GLOBAL_INDEX_REBUILD")
@NamedQuery(name="GlobalIndexRebuild.findAll", query="SELECT g FROM GlobalIndexRebuild g")
public class GlobalIndexRebuild implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="INDEX_NAME")
	private String indexName;

	@Column(name="SQL_REBUILD")
	private String sqlRebuild;

	@Column(name="TABLE_NAME")
	private String tableName;

	private String uniqueness;

	public GlobalIndexRebuild() {
	}

	public String getIndexName() {
		return this.indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getSqlRebuild() {
		return this.sqlRebuild;
	}

	public void setSqlRebuild(String sqlRebuild) {
		this.sqlRebuild = sqlRebuild;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUniqueness() {
		return this.uniqueness;
	}

	public void setUniqueness(String uniqueness) {
		this.uniqueness = uniqueness;
	}

}