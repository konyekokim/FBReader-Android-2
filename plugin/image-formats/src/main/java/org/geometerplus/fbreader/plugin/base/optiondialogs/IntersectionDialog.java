package org.geometerplus.fbreader.plugin.base.optiondialogs;

import org.fbreader.plugin.format.base.R;
import org.geometerplus.fbreader.plugin.base.ViewHolder;
import org.geometerplus.fbreader.plugin.base.reader.PercentEditor;
import org.geometerplus.fbreader.plugin.base.reader.PluginView.IntersectionsHolder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntersectionDialog extends OptionDialog {
	
	private PercentEditor myXEdit;
	private PercentEditor myYEdit;
	
	public IntersectionDialog(Context context, Intent i) {
		super(context, i);
	}
	
	protected int layoutId() {
		return R.layout.fmt_intersections;
	}
	protected int titleId() {
		return R.string.intersections;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myXEdit = initPercentEditor(R.id.fmt_x_edit, R.string.x, "x");
		myYEdit = initPercentEditor(R.id.fmt_y_edit, R.string.y, "y");

		ViewHolder.getInstance().getView().setDrawIntersections(true);
	}
	
	@Override
	protected void onStop() {
		ViewHolder.getInstance().getView().setDrawIntersections(false);
		super.onStop();
	}

	private PercentEditor initPercentEditor(int id, int resourceId, String key) {
		final int value = myIntent.getIntExtra(key, 10);
		final PercentEditor editor = (PercentEditor)findViewById(id);
		editor.init(getContext().getResources().getString(resourceId), value, 0, 99);
		editor.setListener(this);
		return editor;
	}

	@Override
	public void onPercentChanged() {
		final int x = myXEdit.getValue();
		final int y = myYEdit.getValue();
		ViewHolder.getInstance().getView().setIntersections(new IntersectionsHolder(x, y));
	}
}
