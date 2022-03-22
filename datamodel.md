# Submission App - Data Model

## Main Document

The Volt app generates data in an NSF. The data model of a submission is described here.

|Field name|Type|Purpose|
|---|---|---|
|F_Attachment1|text, multi|Stores the reference data to an uploaded file|
|F_Email|text|Holds the e-mail of the submitter|
|F_Name|text|The name of the submitter|
|F_PresentationUrl|text|URL to a presentation, e.g. on Slideshare|
|F_SelectOne1|text|Copyright info|
|F_SelectOne2|text|ICLA/CCLA info|
|F_SingleLine1|text|Creator of the presentation|
|F_Title|text|Presentation title|
|F_YoutubeUrl|text|URL to a Youtube video that extends the presentation|

Field names are subject of changes, esp. the ones with anonymous names.

## Attachments

Please note that Volt stores file attachments in separate documents. Those reference to the main document via a UNID or a uuid. If you look into the fields you will get the idea.
