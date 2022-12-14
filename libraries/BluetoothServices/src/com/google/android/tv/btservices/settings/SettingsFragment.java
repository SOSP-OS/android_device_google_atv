/*
 * Copyright (C) 2021 The Android Open Source Project
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

package com.google.android.tv.btservices.settings;

import android.app.Fragment;
import androidx.leanback.preference.LeanbackPreferenceFragment;
import androidx.leanback.preference.LeanbackSettingsFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragment;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends LeanbackSettingsFragment {

    private LeanbackPreferenceFragment mPreferenceFragment;

    public static SettingsFragment newInstance(LeanbackPreferenceFragment fragment) {
        return new SettingsFragment(fragment);
    }

    public SettingsFragment() {
        this(null);
    }

    private SettingsFragment(LeanbackPreferenceFragment fragment) {
        mPreferenceFragment = fragment;
    }

    @Override
    public void onPreferenceStartInitialScreen() {
        if (mPreferenceFragment != null) {
            startPreferenceFragment(mPreferenceFragment);
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        final Fragment f =
                Fragment.instantiate(getActivity(), pref.getFragment(), pref.getExtras());
        f.setTargetFragment(caller, 0);
        if (f instanceof PreferenceFragment || f instanceof PreferenceDialogFragment) {
            startPreferenceFragment(f);
        } else {
            startImmersiveFragment(f);
        }
        return true;
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragment preferenceFragment,
            PreferenceScreen preferenceScreen) {
        return false;
    }
}
