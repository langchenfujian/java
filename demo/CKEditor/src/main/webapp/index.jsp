<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<script src="ckeditor/ckeditor.js"></script>
		<style>

			.cke_button__inputtextbutton_icon
			{
				display: none !important;
			}
	
			.cke_button__inputtextbutton_label
			{
				display: inline !important;
			}
	
		</style>
		<script type="text/javascript">
			CKEDITOR.on( 'instanceCreated', function( ev ){
				var editor = ev.editor;
	
				// Listen for the "pluginsLoaded" event, so we are sure that the
				// "dialog" plugin has been loaded and we are able to do our
				// customizations.
				editor.on( 'pluginsLoaded', function() {
	
					// If our custom dialog has not been registered, do that now.
					if ( !CKEDITOR.dialog.exists( 'inputTextDialog' ) ) {
						// We need to do the following trick to find out the dialog
						// definition file URL path. In the real world, you would simply
						// point to an absolute path directly, like "/mydir/mydialog.js".
						var href = document.location.href.split( '/' );
						href.pop();
						href.push( 'inputTextDialog.js' );
						href = href.join( '/' );
	
						// Finally, register the dialog.
						CKEDITOR.dialog.add( 'inputTextDialog', href );
					}
	
					// Register the command used to open the dialog.
					editor.addCommand( 'inputTextDialogCmd', new CKEDITOR.dialogCommand( 'inputTextDialog' ) );
	
					// Add the a custom toolbar buttons, which fires the above
					// command..
					editor.ui.add( 'inputTextButton', CKEDITOR.UI_BUTTON, {
						label: 'Input Text',
						command: 'inputTextDialogCmd'
					});
				});
			});
			
			var config = {
				extraAllowedContent : 'input[*]'
			};
		</script>
	</head>
	<body>
		<form action="sample_posteddata.html" method="post">
			<p>
				<label for="editor1">Editor 1:</label>
				<textarea cols="80" id="editor1" name="htmlResource" rows="10"></textarea>
			</p>
			<p>
				<input type="submit" value="Submit" />
			</p>
		</form>
		<script type="text/javascript">
			CKEDITOR.replace( 'editor1', config );
		</script>
	</body>
</html>