/*
 * Copyright (C) 2009-2015 FBReader.ORG Limited <contact@fbreader.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.android.fbreader.preferences;

import java.io.*;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import org.fbreader.md.MDDialogPreference;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;
import org.geometerplus.zlibrary.core.resources.ZLResource;
import org.geometerplus.zlibrary.ui.android.R;

class ThirdPartyLibrariesPreference extends MDDialogPreference {
	ThirdPartyLibrariesPreference(Context context, ZLResource resource, String key) {
		super(context);
		setTitle(resource.getResource(key).getValue());
	}

	@Override
	protected String positiveButtonText() {
		return ZLResource.resource("dialog").getResource("button").getResource("ok").getValue();
	}

	@Override
	protected int dialogLayoutId() {
		return R.layout.third_party_libraries;
	}

	@Override
	protected void onBindDialogView(View view) {
		final TextView textView = (TextView)view.findViewById(R.id.third_party_libraries);
		final StringBuilder html = new StringBuilder();
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(
				ZLFile.createFileByPath("data/licences.html").getInputStream()
			));
			String line;
			while ((line = reader.readLine()) != null) {
				html.append(line);
			}
			reader.close();
		} catch (IOException e) {
		}
		textView.setText(Html.fromHtml(html.toString()));
		textView.setMovementMethod(new LinkMovementMethod());
	}
}
