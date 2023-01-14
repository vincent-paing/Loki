# Element

An element represents a peiece of UI information. In Loki, every object is an element which always has type that efines
what type of the element it is.

| Properties   | Description                      | Type               | Required? | 
|--------------|----------------------------------|--------------------|-----------|
| type         | type of the element              | ElementType        | YES       |
| style        | view style of the element        | ViewStyle          | NO        |
| interactions | See Element interactions section | ElementInteraction | NO        |

## Element Types
- Text
- Image
- Button
- Box
- Row
- Column
- Card
- LazyList
- LazyGrid

## Text

Text represents a textual information

| Properties | Description         | Type      | Required? | 
|------------|---------------------|-----------|-----------|
| text       | Text to be rendered | string    | YES       |
| textStyle  | Styling or the text | TextStyle | YES       |

## Image

An image represents a picture.

| Properties | Description                      | Type   | Required? | 
|------------|----------------------------------|--------|-----------|
| url        | A link to image to be rendered   | string | YES       |
| altText    | Alternate text for accessibility | string | NO        | 

## Button

A button represents an element that can be clicked.

| Properties  | Description                                           | Type                             | Required? | 
|-------------|-------------------------------------------------------|----------------------------------|-----------|
| text        | Text to be rendered                                   | string                           | YES       |
| textStyle   | Styling for the text                                  | TextStyle                        | YES       |
| buttonStyle | Define what style the button to be used               | 'filled' or 'outlined' or 'text' | YES       |
| color       | Color used by button to render outline and background | Color HEX String                 | YES       |

## Box

An element container used for styling purpose

| Properties | Description          | Type    | Required? | 
|------------|----------------------|---------|-----------|
| child      | child to be rendered | Element | NO        |

## Row

A layout element that arrange its child elements in a row/horizontally

| Properties | Description                  | Type            | Required? | 
|------------|------------------------------|-----------------|-----------|
| children   | children element to rendered | list of Element | YES       |

## Column

A layout element that arrange its child elements in a column/vertically

| Properties | Description                  | Type            | Required? | 
|------------|------------------------------|-----------------|-----------|
| children   | children element to rendered | list of Element | YES       |

## Card

A card represents a card used in material design

| Properties   | Description                        | Type     | Required? |
|--------------|------------------------------------|----------|-----------|
| cornerRadius | corner radius value, defaults to 0 | number   | NO        |
| child        | child element to rendered          | Element  | YES       |

## LazyList

A lazy list renders it child lazily

| Properties  | Description                                    | Type                        | Required? |
|-------------|------------------------------------------------|-----------------------------|-----------|
| orientation | determines how to order its children elements  | 'horizontal' or 'vertical'  | YES       |
| children    | children element to rendered                   | list of LazyElement         | YES       |

### Lazy Element

Lazy element has an id that is unique so that frontends can use this key to diff list. The id would mostly be the id of the data you're presenting

| Properties | Description                | Type    | Required? |
|------------|----------------------------|---------|-----------|
| id         | unique id for list diffing | string  | YES       |
| element    | element to be rendered     | Element | YES       |

## LazyGrid

A lazy list renders it child lazily

| Properties     | Description                                   | Type                        | Required? |
|----------------|-----------------------------------------------|-----------------------------|-----------|
| orientation    | determines how to order its children elements | 'horizontal' or 'vertical'  | YES       |
| numOfRowColumn | total number of row or column                 | number                      | YES       |
| children       | children element to rendered                  | list of LazyElement         | YES       |

# Element Metadata

## Element Style

A common styling that can be used across all elements.

| Properties | Description                                      | Type            | Required? |
|------------|--------------------------------------------------|-----------------|-----------|
| width      | width of the image, wrap the content by default  | number OR 'max' | NO        |
| height     | height of the image, wrap the content by default | number OR 'max' | NO        |
| padding    | Padding to be applied around the element         | Padding         | NO        |

## Padding

Spacing around an element

| Properties | Description                   | Type   | Required? |
|------------|-------------------------------|--------|-----------|
| top        | top padding, defaults to 0    | number | NO        |
| bottom     | bottom padding, defaults to 0 | number | NO        |
| left       | left padding, defaults to 0   | number | NO        |
| right      | right padding, defaults to 0  | number | NO        |

## Text Style

Styling for element that requires a text to be rendered. The style is to be applied on the text and not on the element
itself.

| Properties | Description                         | Type             | Required? | 
|------------|-------------------------------------|------------------|-----------|
| textSize   | Size of text                        | number           | YES       |
| isBold     | Bold style or not, default to false | boolean          | NO        |
| textColor  | Color of text                       | Color HEX String | NO        |

# Element Interaction

Interaction allows users to interact with the UI elements. This could behavior like clicks, swipe etc.


| Properties | Description                   | Type               | Required? | 
|------------|-------------------------------|--------------------|-----------|
| onPress    | handler for press interaction | InteractionHandler | NO        |

## InteractionHandler

Different InteractionHandler can have different properties depending on type.

| Properties | Description         | Type                   | Required? | 
|------------|---------------------|------------------------|-----------|
| type       | type of interaction | InteractionHandlerType | NO        |

### InteractionHandlerType

- Navigation

## Navigation InteractionHandler

Navigation tells the UI to navigate away from current page

| Properties | Description                     | Type   | Required? | 
|------------|---------------------------------|--------|-----------|
| page       | Name of the page to navigate to | string | YES       |