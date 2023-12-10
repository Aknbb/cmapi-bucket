define({ "api": [
  {
    "type": "post",
    "url": "/messages/addMessage",
    "title": "Adds cmapi message",
    "version": "0.0.1",
    "name": "AddMessage",
    "group": "Cmapi",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Object",
            "optional": false,
            "field": "cmapi",
            "description": "<p>message</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/com/aknbb/cmapibucket/rest/MessageController.java",
    "groupTitle": "Cmapi"
  },
  {
    "type": "delete",
    "url": "/messages/:channel/:title",
    "title": "Deletes given channel:title cmapi message",
    "version": "0.0.1",
    "name": "DeleteMessage",
    "group": "Cmapi",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "examples": [
      {
        "title": "Example usage:",
        "content": "curl -i http://localhost/messages/map.feature.plot/sample-message",
        "type": "json"
      }
    ],
    "filename": "./src/main/java/com/aknbb/cmapibucket/rest/MessageController.java",
    "groupTitle": "Cmapi"
  },
  {
    "type": "get",
    "url": "/messages",
    "title": "Get all cmapi messages as json",
    "version": "0.0.1",
    "name": "GetAllMessages",
    "group": "Cmapi",
    "examples": [
      {
        "title": "Example usage:",
        "content": "curl -i http://localhost/messages",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "messages",
            "description": "<p>Messages data contains channels and cmapi payloads</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/com/aknbb/cmapibucket/rest/MessageController.java",
    "groupTitle": "Cmapi"
  },
  {
    "type": "get",
    "url": "/messages/:channel",
    "title": "Get all cmapi messages of given channel as json",
    "version": "0.0.1",
    "name": "GetChannelMessages",
    "group": "Cmapi",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "examples": [
      {
        "title": "Example usage:",
        "content": "curl -i http://localhost/messages/map.feature.plot",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "messages",
            "description": "<p>Contains cmapi payloads of given channel</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/com/aknbb/cmapibucket/rest/MessageController.java",
    "groupTitle": "Cmapi"
  },
  {
    "type": "get",
    "url": "/systeminfo",
    "title": "Returns application, monitoring and build infos.",
    "version": "0.0.1",
    "name": "GetSystemInfo",
    "group": "Cmapi",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "examples": [
      {
        "title": "Example usage:",
        "content": "curl -i http://localhost/systeminfo",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "systeminfo",
            "description": "<p>Contains application, monitoring and build infos.</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/com/aknbb/cmapibucket/rest/SystemInfoController.java",
    "groupTitle": "Cmapi"
  }
] });
