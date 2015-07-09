package classnamecopier.popup.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CopyClassNameAction implements IObjectActionDelegate {
	private Shell shell;
	private IWorkbenchPart targetPart;

	/**
	 * Constructor for Action1.
	 */
	public CopyClassNameAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		this.targetPart = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Object firstElement = ((IStructuredSelection) targetPart.getSite().getSelectionProvider().getSelection()).getFirstElement();
		CompilationUnit compilationUnit = (CompilationUnit) firstElement;
		char[][] packageNameChar = compilationUnit.getPackageName();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < packageNameChar.length; i++) {
			for (int j = 0; j < packageNameChar[i].length; j++) {
				sb.append(packageNameChar[i][j]);
			}
			sb.append(".");
		}
		String packageName = sb.toString();
		String className = compilationUnit.getElementName().replaceAll("\\.\\w+", "");
		StringSelection stsel = new StringSelection(packageName + className);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
