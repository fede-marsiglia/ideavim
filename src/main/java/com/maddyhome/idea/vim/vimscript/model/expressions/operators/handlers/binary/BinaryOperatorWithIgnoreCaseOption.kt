/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2021 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.vimscript.model.expressions.operators.handlers.binary

import com.maddyhome.idea.vim.VimPlugin
import com.maddyhome.idea.vim.vimscript.model.datatypes.VimDataType
import com.maddyhome.idea.vim.vimscript.services.OptionService

abstract class BinaryOperatorWithIgnoreCaseOption(
  private val caseInsensitiveImpl: BinaryOperatorHandler,
  private val caseSensitiveImpl: BinaryOperatorHandler,
) : BinaryOperatorHandler() {

  private fun shouldIgnoreCase(): Boolean {
    return VimPlugin.getOptionService().isSet(OptionService.Scope.GLOBAL, "ignorecase", null)
  }

  override fun performOperation(left: VimDataType, right: VimDataType): VimDataType {
    return if (shouldIgnoreCase()) caseInsensitiveImpl.performOperation(left, right) else
      caseSensitiveImpl.performOperation(left, right)
  }
}
