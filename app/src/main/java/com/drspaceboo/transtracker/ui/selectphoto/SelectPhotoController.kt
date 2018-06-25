/*
 * Copyright © 2018 TransTracks. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.drspaceboo.transtracker.ui.selectphoto

import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.drspaceboo.transtracker.R
import com.drspaceboo.transtracker.util.plusAssign
import io.reactivex.disposables.CompositeDisposable

class SelectPhotoController : Controller() {
    private val viewDisposables: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        return inflater.inflate(R.layout.select_photo, container, false)
    }

    override fun onAttach(view: View) {
        if (view !is SelectPhotoView) throw AssertionError("View must be SelectPhotoView")

        val sharedEvents = view.events.share()

        viewDisposables += sharedEvents.subscribe { event ->
            when (event) {
                SelectPhotoUiEvent.Back -> router.handleBack()
            }
        }
    }

    override fun onDetach(view: View) {
        viewDisposables.clear()
    }
}
