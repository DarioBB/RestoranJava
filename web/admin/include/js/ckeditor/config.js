/**
 * @license Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	CKEDITOR.config.filebrowserBrowseUrl = '../admin/include/js/kcfinder/browse.php?type=files';
	CKEDITOR.config.filebrowserImageBrowseUrl = '../admin/include/js/kcfinder/browse.php?type=images';
	CKEDITOR.config.filebrowserFlashBrowseUrl = '../admin/include/js/kcfinder/browse.php?type=flash';
	CKEDITOR.config.filebrowserUploadUrl = '../admin/include/js/kcfinder/upload.php?type=files';
	CKEDITOR.config.filebrowserImageUploadUrl = '../admin/include/js/kcfinder/upload.php?type=images';
	CKEDITOR.config.filebrowserFlashUploadUrl = '../admin/include/js/kcfinder/upload.php?type=flash';
	
	CKEDITOR.config.width = 800;
	CKEDITOR.config.height = 500;
	config.toolbar_Basic =
	[
		['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']
	];
	config.toolbar_Full =
	[
		{ name: 'document', items : [ 'Source','-','Save','NewPage','DocProps','Preview','Print','-','Templates' ] },
		{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		{ name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
		{ name: 'forms', items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 
			'HiddenField' ] },
		'/',
		{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
		'-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		{ name: 'links', items : [ 'Link','Unlink','Anchor' ] },
		{ name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ] },
		'/',
		{ name: 'tools', items : [ 'Maximize', 'ShowBlocks','-','About' ] }
	];
	CKEDITOR.config.toolbar = 'toolbar_Full';
	CKEDITOR.config.forcePasteAsPlainText = true;
};
