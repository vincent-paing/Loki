## Element

An element represents a peiece of UI information. In Loki, every object is an element which always has type that efines
what type of the element it is.

| Properties | Description               | Type        | Required? | 
|------------|---------------------------|-------------|-----------|
| type       | type of the element       | ElementType | YES       |
| style      | view style of the element | ViewStyle   | NO        |

## Element Types
- Text
- Image
- Button
- Box
- Row
- Column
- Card
- LazyList


## Text

Text represents a textual information

| Properties | Description          | Type      | Required? | 
|------------|----------------------|-----------|-----------|
| text       | Text to be rendered  | string    | YES       |
| textStyle  | Styling for the text | TextStyle | YES       |

## Image

An image represents a picture.

| Properties | Description                      | Type   | Required? | 
|------------|----------------------------------|--------|-----------|
| url        | A link to image to be rendered   | string | YES       |
| altText    | Alternate text for accessibility | string | NO        | 

## Button

A button represents an element that can be clicked.

| Properties | Description          | Type      | Required? | 
|------------|----------------------|-----------|-----------|
| text       | Text to be rendered  | string    | YES       |
| textStyle  | Styling for the text | TextStyle | YES       |

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

| Properties        | Description                        | Type            | Required? |
|-------------------|------------------------------------|-----------------|-----------|
| roundCornerRadius | corner radius value, defaults to 0 | number          | NO        |
| children          | children element to rendered       | list of Element | YES       |

## LazyList

A lazy list renders it child lazily

| Properties  | Description                                    | Type                       | Required? |
|-------------|------------------------------------------------|----------------------------|-----------|
| orientation | deteremines how to order its children elements | 'horizontal' or 'vertical' | YES       |
| children    | children element to rendered                   | list of Element            | YES       |

## Element Metadatas

### Element Style

A common styling that can be used across all elements.

| Properties | Description                                      | Type            | Required? |
|------------|--------------------------------------------------|-----------------|-----------|
| width      | width of the image, wrap the content by default  | number OR 'max' | NO        |
| height     | height of the image, wrap the content by default | number OR 'max' | NO        |
| padding    | Padding to be applied around the element         | Padding         | NO        |

### Padding

Spacing around an element

| Properties | Description                   | Type   | Required? |
|------------|-------------------------------|--------|-----------|
| top        | top padding, defaults to 0    | number | NO        |
| bottom     | bottom padding, defaults to 0 | number | NO        |
| left       | left padding, defaults to 0   | number | NO        |
| right      | right padding, defaults to 0  | number | NO        |

### Text Style

Styling for element that requires a text to be rendered. The style is to be applied on the text and not on the element
itself.

| Properties | Description                          | Type     | Required? | 
|------------|--------------------------------------|----------|-----------|
| textSize   | Size of text                         | number   | YES       |
| isBold     | Bold style or not, default to false  | boolean  | NO        |