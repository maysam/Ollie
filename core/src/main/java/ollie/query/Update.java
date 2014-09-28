/*
 * Copyright (C) 2014 Michael Pardo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ollie.query;

import ollie.Model;
import ollie.Ollie;

public final class Update extends QueryBase {
	public Update(Class<? extends Model> table) {
		super(null, table);
	}

	public Set set(String set) {
		return set(set, (String[]) null);
	}

	public Set set(String set, String... args) {
		return new Set(this, mTable, set, args);
	}

	@Override
	public String getPartSql() {
		return "UPDATE " + Ollie.getTableName(mTable);
	}

	public static final class Set extends ExecutableQueryBase {
		private String mSet;
		private String[] mSetArgs;

		private Set(Query parent, Class<? extends Model> table, String set, String... args) {
			super(parent, table);
			mSet = set;
			mSetArgs = args;
		}

		public Where where(String where) {
			return where(where, (String[]) null);
		}

		public Where where(String where, String... args) {
			return new Where(this, mTable, where, args);
		}

		@Override
		public String getPartSql() {
			return "SET " + mSet;
		}

		@Override
		public String[] getPartArgs() {
			return mSetArgs;
		}
	}

	public static final class Where extends ExecutableQueryBase {
		private String mWhere;
		private String[] mWhereArgs;

		public Where(Query parent, Class<? extends Model> table, String where, String[] args) {
			super(parent, table);
			mWhere = where;
			mWhereArgs = args;
		}

		@Override
		public String getPartSql() {
			return "WHERE " + mWhere;
		}

		@Override
		public String[] getPartArgs() {
			return mWhereArgs;
		}
	}
}
