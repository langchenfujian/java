/**
 * Test inputDialog
 */
CKEDITOR.dialog.add( 'inputTextDialog', function( editor ) {
	return {
		title: 'Input Text Dialog',
		minWidth: 400,
		minHeight: 200,
		onOk: function(){
			// 1. Assemble html
			// 2. Insert upper html into CKEditor instance
			var formName = this.getValueOf('HTML','formName');
			var defaultValue = this.getValueOf('HTML','defaultValue');
			var bizFieldName = this.getValueOf('BUSINESS','bizFieldName');
			var bizFieldValueType = this.getValueOf('BUSINESS','bizFieldValueType');
			var isControlProcess = this.getValueOf('BUSINESS','isControlProcess');
			var htmlStr = '<input type="text" name="'+formName+'" value="'+defaultValue+'" bizFieldName="'+bizFieldName+'" bizFieldValueType="'+bizFieldValueType+'" isControlProcess="'+isControlProcess+'" />';
			if ( editor.mode == 'wysiwyg' )
			{
				this.getParentEditor().insertHtml(htmlStr);
			}
			else{
				alert( 'You must be in WYSIWYG mode!' );
			}
		},
		contents: [
			{
				id: 'HTML',
				label: 'HTML',
				title: 'HTML',
				elements: [
					{
						id: 'formName',
						type: 'text',
						label: 'Name'
					},{
						id: 'defaultValue',
						type: 'text',
						label: 'Default Value'
					}
				]
			},
			{
				id: 'BUSINESS',
				label: 'BUSINESS',
				title: 'BUSINESS',
				elements: [
					{
						id: 'bizFieldName',
						type: 'text',
						label: 'Field Name'
					},{
						id: 'bizFieldValueType',
						type: 'select',
						label: 'Field Name Type',
						items: [
								[ 'java.lang.String', 'String' ],
								[ 'java.lang.Integer', 'Integer' ]
							]
					},{
						id: 'isControlProcess',
						label: 'Is control process?',
						type: 'checkbox'
					}
				]
			}
		]		
	};
} );